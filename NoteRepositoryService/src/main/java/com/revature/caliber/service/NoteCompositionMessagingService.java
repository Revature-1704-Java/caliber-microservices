package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class NoteCompositionMessagingService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_BATCH_ROUTING_KEY = "XLNbCWqQzFHr9JfZ";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	
	public boolean sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();
		
		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);
		
		String batch = (String) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY, batchRequest.toString());
		
		return true;
	}
	
	public boolean sendSingleSimpleTraineeRequest(Integer traineeId) {
		JsonObject traineeRequest = new JsonObject();
		
		traineeRequest.addProperty("methodName", "findOne");
		traineeRequest.addProperty("traineeId", traineeId);
		
		String trainee = (String) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY, traineeRequest.toString());
		
		return true;
	}
}
