package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;

@Service
public class AssessmentCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	private static final String SINGLE_BATCH_ROUTING_KEY = "XLNbCWqQzFHr9JfZ";
	private static final String SINGLE_CATEGORY_ROUTING_KEY = "utMPxDus2M9qy9Bh";
	private static final String SINGLE_GRADE_ROUTING_KEY = "aYF4wPtsGMjq72Lu";

	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchFindRequest = new JsonObject();

		batchFindRequest.addProperty("methodName", "findOne");
		batchFindRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchFindRequest.toString());
	}

	public SimpleCategory sendSingleSimpleCategoryRequest(Integer categoryId) {
		JsonObject categoryFindRequest = new JsonObject();

		categoryFindRequest.addProperty("methodName", "findOne");
		categoryFindRequest.addProperty("categoryId", categoryId);

		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_CATEGORY_ROUTING_KEY,
				categoryFindRequest.toString());
	}

	public void sendGradeDeleteRequestForAssessmentId(Long assessmentId) {
		JsonObject gradeDeleteRequest = new JsonObject();

		gradeDeleteRequest.addProperty("methodName", "delete");
		gradeDeleteRequest.addProperty("assessmentId", assessmentId);

		rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_GRADE_ROUTING_KEY,
				gradeDeleteRequest.toString());
	}

	public SimpleBatch sendSingleSimpleBatchRequest(String resourceId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOneByResourceId");
		batchRequest.addProperty("resourceId", resourceId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	public SimpleCategory sendSingleSimpleCategoryRequest(String category) {
		JsonObject catRequest = new JsonObject();

		catRequest.addProperty("methodName", "findOneBySkillCategory");
		catRequest.addProperty("category", category);

		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_CATEGORY_ROUTING_KEY,
				catRequest.toString());
	}

}