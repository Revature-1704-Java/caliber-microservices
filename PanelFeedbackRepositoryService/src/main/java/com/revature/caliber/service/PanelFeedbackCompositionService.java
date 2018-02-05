package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.Panel;
import com.revature.caliber.model.PanelFeedback;
import com.revature.caliber.model.PanelStatus;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.model.SimplePanelFeedback;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.PanelFeedbackRepository;

public class PanelFeedbackCompositionService {

	@Autowired
	private PanelFeedbackRepository panelFeedbackRepository;
	@Autowired
	private PanelFeedbackCompositionMessagingService panelFeedbackCompositionMessagingService;
	
	//findOne
	public PanelFeedback findOne(Long panelFeedbackId) {
		SimplePanelFeedback basis = panelFeedbackRepository.findOne(panelFeedbackId);
		PanelFeedback result = composePanelFeedback(basis);
		
		return result;
	}
	
	//findAll
	public List<PanelFeedback> findAll() {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findAll();
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	//findAllForPanel
	public List<PanelFeedback> findAllForPanel(int panelId) {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findById(panelId);
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	//findFailedFeedbackByPanel
	public List<PanelFeedback> findFailedFeedbackByPanel(int panelId) {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findByIdAndStatus(panelId, PanelStatus.Repanel);
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	/*
	//save
	public ? save(PanelFeedback panelFeedback) {
		
	}
	
	//update
	public ? update(PanelFeedback panelFeedback) {
		
	}
	
	//delete
	public ? delete(Long panelFeedbackd) {
		
	}
	*/
	
	private List<PanelFeedback> composeListOfPanelFeedback(List<SimplePanelFeedback> src) {
		List<PanelFeedback> dest = new LinkedList<PanelFeedback>();
		
		for(SimplePanelFeedback curr : src) {
			PanelFeedback panelFeedBack = new PanelFeedback(curr);
			dest.add(new PanelFeedback(curr));
			//if(!includeDropped && note.getTrainee().getTrainingStatus() != TrainingStatus.Dropped)
				//dest.add(new PanelFeedback(curr));
			//else if(includeDropped)
				//dest.add(new PanelFeedback(curr));
		}
		
		return dest;
	}
	
	private PanelFeedback composePanelFeedback(SimplePanelFeedback src) {
		SimpleCategory simpleCategory = panelFeedbackCompositionMessagingService.sendSingleSimpleCategoryRequest(src.getCategoryId());
		SimplePanel simplePanel = panelFeedbackCompositionMessagingService.sendSingleSimplePanelRequest(src.getPanelId());
		Category category = new Category(simpleCategory);
		Panel panel = new Panel(simplePanel);
		PanelFeedback dest = new PanelFeedback(src);
		
		dest.setTechnology(category);
		dest.setPanel(panel);
		
		return dest;
	}
}
