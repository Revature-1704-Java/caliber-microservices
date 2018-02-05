package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;

@Service
public class TraineeCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String SINGLE_BATCH_ROUTING_KEY = "XLNbCWqQzFHr9JfZ";
	private static final String LIST_BATCH_ROUTING_KEY = "BSVihZkuxwdg9Dxy";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}
	
	public List<SimpleBatch> sendListSimpleBatchRequest(Integer trainerId) {
		JsonObject batchRequest = new JsonObject();
		batchRequest.addProperty("methodName", "findAllByTrainerId");
		batchRequest.addProperty("trainerId", trainerId);
		
		return (List<SimpleBatch>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_BATCH_ROUTING_KEY, batchRequest.toString());
	}
}