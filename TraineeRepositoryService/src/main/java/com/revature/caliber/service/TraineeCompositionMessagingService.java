package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;

@Service
public class TraineeCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String SINGLE_BATCH_ROUTING_KEY = "F82jS9KJpwqLk3dj";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	public SimpleCategory sendSingleSimpleCategoryRequest(Integer categoryId) {
		JsonObject categoryRequest = new JsonObject();

		categoryRequest.addProperty("methodName", "findOne");
		categoryRequest.addProperty("categoryId", categoryId);

		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				categoryRequest.toString());
	}
}