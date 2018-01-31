package com.revature.caliber.service;

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
	
	@RabbitListener(queues = "caliber.note")
	public SimplePanelFeedback receive(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		
		//FindOne
		if(request.get("methodName").getAsString().equals("findOne")) {
			SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
			return panelFeedback;
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
		else if(request.get("methodName").getAsString().equals("delete")) {
			SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
			panelFeedbackRepository.delete(request.get("panelFeedbackId").getAsLong());
			return panelFeedback; //+ Message for delete? Notify others?
		} 
		else {
			return null;
		}
	}
	
	//Lists
	/*
	 * findAll
	 * else if(request.get("methodName").getAsString().equals("findAll")) {
			List<SimplePanelFeedback> panelFeedbacks = panelFeedbackRepository.findAll();
			return panelFeedbacks; //+ Message for delete?
		} 
	 * 
	 * findAllForPanel(int panelId)
	 * else if(request.get("methodName").getAsString().equals("findAllForPanel")) {
			List<SimplePanelFeedback> panelFeedbacks = panelFeedbackRepository.findAllForPanel(request.get("panelId").getAsInt());
			return panelFeedbacks; //+ Message for delete?
		} 
	 * 
	 * findFailedFeedbackByPanel(panel panel)
	 * else if(request.get("methodName").getAsString().equals("findFailedFeedbackByPanel")) {
			List<SimplePanelFeedback> panelFeedback = panelFeedbackRepository.findFailedFeedbackByPanel(request.get("panelId").getAsInt());
			panelFeedbackRepository.delete(request.get("panelFeedbackId").getAsLong());
			return panelFeedbacks;
		} 
	 * 
	 */
}
