package com.revature.caliber.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Panel;
import com.revature.caliber.model.PanelFeedback;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.SimpleTrainer;
// import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.model.TrainingStatus;
// import com.revature.caliber.model.Trainer;
import com.revature.caliber.repository.PanelRepository;

public class PanelCompositionService {

	@Autowired
	private PanelRepository panelRepository;
	@Autowired
	private PanelCompositionMessagingService panelCompositionMessagingService;

	public Panel findOne(Integer panelId) {
		SimplePanel basis = panelRepository.findOne(panelId);
		Panel result = composePanel(basis);

		return result;
	}

	public List<Panel> findAll() {
		List<SimplePanel> basis = panelRepository.findAll();
		List<Panel> result = composeListOfPanels(basis, false);

		return result;
	}

	public List<Panel> findAllRepanel() {
		List<SimplePanel> basis = panelRepository.findAllRepanels();
		List<Panel> result = composeListOfPanels(basis, false);

		return result;

	}


	public List<Panel> findBiWeeklyPanels() {
		List<SimplePanel> basis = panelRepository.findRecentPanels();
		List<Panel> result = composeListOfPanels(basis, false);

		return result;
	}

	public List<Panel> findAllByTrainee(Integer traineeId) {
		List<SimplePanel> basis = panelRepository.findByTraineeId(traineeId);
		List<Panel> result = composeListOfPanels(basis, false);

		return result;
	}

	// move to trainee
	// public List<Trainee> findAllTraineesAndPanels(Integer batchId) {
	// List<SimpleTrainee> basis =
	// panelRepository.findAllTraineesAndPanelsByBatch(batchId);
	// List<Trainee> result = composeListOfPanels(basis, false);
	//
	// return result;
	// }

	public void save(Panel panel) {
		SimplePanel simplePanel = new SimplePanel(panel);
	 
		panelRepository.save(simplePanel);
	}
	
	public void update(Panel panel) {
		save(panel);
	}
	
	public void delete(Integer panelId) {
		if(panelRepository.findOne(panelId) != null) {
			panelRepository.delete(panelId);
			panelCompositionMessagingService.sendPanelFeedbackDeleteRequest(panelId);
		}
	}

	private List<Panel> composeListOfPanels(List<SimplePanel> src, boolean includeDropped) {
		List<Panel> dest = new LinkedList<Panel>();

		for (SimplePanel curr : src) {
			Panel panel = new Panel(curr);

			if (!includeDropped && panel.getTrainee().getTrainingStatus() != TrainingStatus.Dropped)
				dest.add(new Panel(curr));
			else if (includeDropped)
				dest.add(new Panel(curr));
		}
		return dest;
	}

	private Panel composePanel(SimplePanel src) {
		SimpleTrainee simpleTrainee = panelCompositionMessagingService
				.sendSingleSimpleTraineeRequest(src.getTraineeId());
		SimpleTrainer simpleTrainer = panelCompositionMessagingService
				.sendSingleSimpleTrainerRequest(src.getPanelist());
		
		panelCompositionMessagingService.sendSingleSimpleTrainerRequest(src.getPanelist());

		Trainee trainee = new Trainee(simpleTrainee);
		Trainer trainer = new Trainer(simpleTrainer);

		Panel dest = new Panel(src);

		dest.setTrainee(trainee);
		dest.setPanelist(trainer);

		return dest;
	}
	private List<Map<String, String>> utilAllTraineePanels(List<Trainee> trainees) {
		Map<String, String> panelInfo;
		List<Map<String, String>> batchPanels = new ArrayList<>();
		for (Trainee t : trainees) {
			panelInfo = new HashMap<>();
			panelInfo.put("trainee", t.getName());
			List<Panel> panels = new ArrayList<>();
			Set<Panel> set = new HashSet<>();
			set = t.getPanelInterviews();
			Panel panel;
			String status;
			if (!panels.isEmpty()) {
				panel = panels.get(0);
				status = panel.getStatus().toString();
				panelInfo.put("status", status);
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy 'at' h:mm a");
				String[] dateTime = df.format(panel.getInterviewDate()).split("at");
				panelInfo.put("date", dateTime[0]);
				panelInfo.put("time", dateTime[1]);
				if(status.equalsIgnoreCase("Repanel")) {
					String topics = utilGetRepanelTopics(panel.getFeedback());
					panelInfo.put("topics", topics);
				}
			}
			batchPanels.add(panelInfo);
		}
		return batchPanels;
	}
	private String utilGetRepanelTopics(Set<PanelFeedback> feedback) {
		String topics = "";
		for(PanelFeedback pf: feedback) {
			if(pf.getStatus().toString().equalsIgnoreCase("Repanel")) {
				if (topics.equals(""))
					topics += pf.getTechnology().getSkillCategory();
				else
					topics += ", " + pf.getTechnology().getSkillCategory();
			}
		}
		return topics;
	}
	
	public List<Map<String, String>> getBatchPanels(List<Trainee> trainee) {
		return utilAllTraineePanels(trainee);
	}
}
