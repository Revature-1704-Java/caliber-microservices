package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleTrainee;

@Service
public class GradeCompositionMessagingService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_ASSESSMENT_ROUTING_KEY = "F82jS9KJpwqLk3dj";
	private static final String SINGLE_TRAINEE_ROUTING_KEY = "JyoH3uRmktGn9MnW";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	
	/*
	 * Dont have the SimpleAssessment so I will skip for now
	public SimpleAssessment sendSingleGradeRequest() {
	       return true;
	}
	*/
	
	public SimpleTrainee sendSimpleTraineeRequest(Integer traineeId) {
		JsonObject traineeRequest = new JsonObject();
		
		traineeRequest.addProperty("methodName", "findOne");
		traineeRequest.addProperty("traineeId", traineeId);
		
		return (SimpleTrainee) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINEE_ROUTING_KEY, traineeRequest.toString());
	}
	
	
	public SimpleAssessment sendSimpleAssessmentRequest(Long assessmentId) {
		JsonObject assessmentRequest = new JsonObject();
		
		assessmentRequest.addProperty("methodName", "findOne");
		assessmentRequest.addProperty("assessmentId", assessmentId);
		
		return (SimpleAssessment) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_ASSESSMENT_ROUTING_KEY, assessmentRequest.toString());
	}
}
