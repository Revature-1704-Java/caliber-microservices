package com.revature.caliber.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Panel;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainee;
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
	
	private Panel composePanel(SimplePanel src) {
		SimpleTrainee simpleTrainee = panelCompositionMessagingService.sendSingleSimpleTraineeRequest(src.getTraineeId());
		SimpleTrainer simpleTrainer = panelCompositionMessagingService.sendSingleSimpleTrainerRequest(src.getTrainerId());
		
		Trainee trainee = new Trainee(simpleTrainee);
		Trainer trainer = new Trainer(simpleTrainer);
		
		Panel dest = new Panel(src);
		
		dest.setTrainee(trainee);
		dest.setTrainer(trainer);
		
		return dest;
	}
}
