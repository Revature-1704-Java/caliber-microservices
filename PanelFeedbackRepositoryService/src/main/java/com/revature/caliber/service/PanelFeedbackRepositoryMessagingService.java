package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimplePanelFeedback;

@Service
public class PanelFeedbackRepositoryMessagingService {

	@Autowired
	private PanelFeedbackRepositoryRequestDispatcher panelFeedbackRepositoryRequestDispatcher;

	@RabbitListener(queues = "revature.caliber.repos.panelfeedback")
	public SimplePanelFeedback receiveSingleSimplePanelFeedbackRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return panelFeedbackRepositoryRequestDispatcher.processSingleSimplePanelFeedbackRequest(request);
	}

	@RabbitListener(queues = "revature.caliber.repos.panelfeedback.list")
	public List<SimplePanelFeedback> receiveListSimplePanelFeedbackRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return panelFeedbackRepositoryRequestDispatcher.processListSimplePanelFeedbackRequest(request);
	}	
}