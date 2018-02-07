package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimplePanelFeedback;
import com.revature.caliber.repository.PanelFeedbackRepository;

/**
 * Processes messages from other services.
 * FindOne and findAll are requests from other services needed
 * to construct their complex beans for the front end.
 * 
 * Delete removes orphaned panel feedbacks if the parent panel is deleted.
 */
@Service
public class PanelFeedbackRepositoryRequestDispatcher {

	@Autowired
	private PanelFeedbackRepository panelFeedbackRepository;
	
	public SimplePanelFeedback processSingleSimplePanelFeedbackRequest(JsonObject request) {
		SimplePanelFeedback result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Long panelFeedbackId = request.get("panelFeedbackId").getAsLong();
			result = panelFeedbackRepository.findOne(panelFeedbackId);
		}
		else if (methodName.equals("delete")) {
			 int panelId = request.get("panelId").getAsInt();
			 panelFeedbackRepository.deleteByPanelId(panelId);
		}
		
		return result;
	}
	
	public List<SimplePanelFeedback> processListSimplePanelFeedbackRequest(JsonObject request) {
		List<SimplePanelFeedback> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = panelFeedbackRepository.findAll();
		}
		
		return result;
	}
}
