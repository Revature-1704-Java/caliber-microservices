package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.Panel;
import com.revature.caliber.model.PanelFeedback;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.model.SimplePanelFeedback;
import com.revature.caliber.repository.PanelFeedbackRepository;

@Service
public class PanelFeedbackRepositoryMessagingService {

	private static final Logger log = Logger.getLogger(PanelFeedbackRepositoryMessagingService.class);
	@Autowired
	private PanelFeedbackRepository panelFeedbackRepository;
	@Autowired
	private PanelFeedbackRepositoryMessagingService mms;
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public SimpleCategory sendCategory(String routingKey, String message) {
		
		SimpleCategory response = (SimpleCategory) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
		if (response != null) {
			System.out.println(response);
			return response;
		}
		System.out.println("Response NOT recieved");
		return null;
	}
	
	public PanelFeedback sendPanel(String routingKey, String message) {
		PanelFeedback response = (PanelFeedback) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
		if (response != null) {
			System.out.println(response);
			return response;
		}
		System.out.println("Response NOT recieved");
		return null;
	}
	
	
	@RabbitListener(queues = "revature.caliber.repos.panelfeedback")
	public PanelFeedback receiveSingle(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		
		System.out.println(request);
		
		//FindOne
		if(request.get("methodName").getAsString().equals("findOne")) {
			//Get SimplePanelFeedback from own DAO
			SimplePanelFeedback simplePanelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
			
			//Message Category for simple bean
			JsonObject categoryMsg = new JsonObject();
			categoryMsg.addProperty("methodName", "findOne");
			categoryMsg.addProperty("categoryId", 1);
			SimpleCategory simpleCategory = mms.sendCategory("utMPxDus2M9qy9Bh", categoryMsg.toString());
			
			//Make "complex category"
			Category category = new Category();
			category.setCategoryId(simpleCategory.getCategoryId());
			category.setSkillCategory(simpleCategory.getSkillCategory());
			category.setActive(simpleCategory.isActive());
			category.setAssessments(null);
			
			/*
			//Message Panel for simple bean
			JsonObject panelMsg = new JsonObject();
			panelMsg.addProperty("methodName", "findOne");
			panelMsg.addProperty("categoryId", 1);
			SimplePanel simplePanel= mms.sendCategory("panelKey", panelMsg.toString());
			*/
			
			/*
			//Make "complex" panel
			Panel panel = new Panel();
			panel.setId(simplePanel.getId());
			panel.setTrainee(null); //Object - Trainee
			panel.setPanelist(null); //Object - Trainer
			panel.setInterviewDate(simplePanel.getInterviewDate());
			panel.setDuration(simplePanel.getDuration());
			panel.setFormat(simplePanel.getFormat());
			panel.setInternet(simplePanel.getInternet());
			panel.setPanelRound(simplePanel.getPanelRound());
			panel.setRecordingConsent(simplePanel.getRecordingConsent());
			panel.setRecordingLink(simplePanel.getRecordingLink());
			panel.setStatus(simplePanel.getStatus());
			panel.setFeedback(null); //List of PanelFeedbacks
			panel.setAssociateIntro(simplePanel.getAssociateIntro());
			panel.setProjectOneDescription(simplePanel.getProjectOneDescription());
			panel.setProjectTwoDescription(simplePanel.getProjectTwoDescription());
			panel.setProjectThreeDescription(simplePanel.getProjectThreeDescription());
			panel.setCommunicationSkills(simplePanel.getCommunicationSkills());
			panel.setOverall(simplePanel.getOverall());
			*/
			
			
			//Compose simple beans into Complex and return
			PanelFeedback panelFeedback = new PanelFeedback();
			panelFeedback.setId(simplePanelFeedback.getId());
			panelFeedback.setTechnology(category);
			panelFeedback.setStatus(simplePanelFeedback.getStatus());
			panelFeedback.setResult(simplePanelFeedback.getResult());
			panelFeedback.setComment(simplePanelFeedback.getComment());
			panelFeedback.setPanel(null);
			
			System.out.println("panelF to return " +panelFeedback.toString());
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
		//else if(request.get("methodName").getAsString().equals("delete")) {
		//	SimplePanelFeedback panelFeedback = panelFeedbackRepository.findOne(request.get("panelFeedbackId").getAsLong());
		//	panelFeedbackRepository.delete(request.get("panelFeedbackId").getAsLong());
		//	return panelFeedback; //+ Message for delete? Notify others?
		//} 
		return null;
	}
	
	/*
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
	//}
	
}
