package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimplePanelFeedback;
import com.revature.caliber.repository.PanelFeedbackRepository;

@Service
public class PanelfeedbackRepositoryMessagingService {

	private static final Logger log = Logger.getLogger(PanelfeedbackRepositoryMessagingService.class);
	@Autowired
	private PanelFeedbackRepository panelFeedbackRepository;
	
	@RabbitListener(queues = "caliber.panelfeedback")
	public SimplePanelFeedback receiveSingle(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		
		System.out.println(request);
		return null;
		
		//FindOne
		//if(request.get("methodName").getAsString().equals("findOne")) {
		//	SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
		//	return panelFeedback;
		//}
		//else if(request.get("methodName").getAsString().equals("findOne")) {
		//	SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelId").getAsLong());
		//	return panelFeedback;
		}
		//update --  what the heck to we expect here? A bean? The updated fields?
		//else if(request.get("methodName").getAsString().equals("update")) {
			//panelFeedbackRepository.save(panelFeedback);
			//SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
			//return panelFeedback; //+ Message for update? Notify others?
		//} 
		//save -- what the heck to we expect here? A bean?
		//else if(request.get("methodName").getAsString().equals("save")) {
			//panelFeedbackRepository.save(panelFeedback);
			//SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
			//return panelFeedback; //+ Message for save? Notify others?
		//}
		//delete
		//else if(request.get("methodName").getAsString().equals("delete")) {
		//	SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
		//	panelFeedbackRepository.delete(request.get("panelFeedbackId").getAsLong());
		//	return panelFeedback; //+ Message for delete? Notify others?
		//} 
		//return null;
	//}
	
	
	@RabbitListener(queues = "caliber.panelfeedback.list")
	public List<SimplePanelFeedback> receiveList(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		
		List<SimplePanelFeedback> panelFeedbacks = new ArrayList<>();
		return panelFeedbacks;
		
		/*
		if(request.get("methodName").getAsString().equals("findAll")) {
			panelFeedbacks = panelFeedbackRepository.findAll();
			return panelFeedbacks; //+ Message for delete?
		}
		
		else if(request.get("methodName").getAsString().equals("findAllForPanel")) {
			panelFeedbacks = panelFeedbackRepository.findAllForPanel(request.get("panelId").getAsInt());
			return panelFeedbacks; //+ Message for delete?
		}
		
		else if(request.get("methodName").getAsString().equals("findFailedFeedbackByPanel")) {
			panelFeedbacks = panelFeedbackRepository.findFailedFeedbackByPanel(request.get("panelId").getAsInt());
			return panelFeedbacks;
		}
		else {
			return null;
		}
		*/
	}
	
}
