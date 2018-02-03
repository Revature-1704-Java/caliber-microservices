package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;

@Service
public class TraineeCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String SINGLE_BATCH_ROUTING_KEY = "JyoH3uRmktGn9MnW";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}
}