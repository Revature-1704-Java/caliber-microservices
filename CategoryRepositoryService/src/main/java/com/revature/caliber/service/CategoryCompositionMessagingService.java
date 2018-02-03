package com.revature.caliber.service;

import com.google.gson.JsonObject;
//import com.revature.caliber.model.SimpleAssessment;

public class CategoryCompositionMessagingService {

	private static final String SINGLE_PANEL_ROUTING_KEY = "7c6tMqRRPpZ8Z7xh";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	/*
	 * Category has a set of Assessments in old bean but not in DB
	 * Assessment has a category
	 * I guess if really needed grab all assessments by the category id
	 * 
	public List<SimpleAssessment> sendSingleSimpleAssessmentRequest(Integer categoryId) {
		JsonObject panelRequest = new JsonObject();
			
		panelRequest.addProperty("methodName", "findOne");
		panelRequest.addProperty("categoryId", categoryId);
			
		return (SimpleAssessment) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_PANEL_ROUTING_KEY, panelRequest.toString());
	}
	*/
}
