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

	private static final String LIST_NOTE_ROUTING_KEY = "cf22J9CGs8V95Rbm";
	private static final String SINGLE_BATCH_ROUTING_KEY = "XLNbCWqQzFHr9JfZ";
	private static final String LIST_BATCH_ROUTING_KEY = "BSVihZkuxwdg9Dxy";
	private static final String LIST_GRADE_ROUTING_KEY = "V6hbpnyZRH8ZQQ9e";
	private static final String LIST_PANEL_ROUTING_KEY = "8AzDbkAUCZn9Z2T3";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	/**
	 * Create message for Batch to get a single Batch
	 *
	 * @param batchId
	 *
	 * @return SimpleBatch
	 */
	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	/**
	 * Create message for Batch to get a List Batches for a given trainerId
	 *
	 * @param traineeId
	 *
	 * @return List of SimpleBatch
	 */
	public List<SimpleBatch> sendListSimpleBatchRequest(Integer trainerId) {
		JsonObject batchRequest = new JsonObject();
		batchRequest.addProperty("methodName", "findAllByTrainerId");
		batchRequest.addProperty("trainerId", trainerId);

		return (List<SimpleBatch>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	/**
	 * Create message for Note to delete Notes associated with a trainee
	 *
	 * @param batchId
	 *
	 * @return 
	 */
	public void sendSimpleNoteDeleteRequest(Integer traineeId) {
		JsonObject NoteDeleteRequest = new JsonObject();
		NoteDeleteRequest.addProperty("methodName", "delete");
		NoteDeleteRequest.addProperty("traineeId", traineeId);
		rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_NOTE_ROUTING_KEY, NoteDeleteRequest.toString());
	}
	
	/**
	 * Create message for Grade to delete Grades associated with a trainee
	 *
	 * @param batchId
	 *
	 * @return 
	 */
	public void sendSimpleGradeDeleteRequest(Integer traineeId) {
		JsonObject GradeDeleteRequest = new JsonObject();
		GradeDeleteRequest.addProperty("methodName", "delete");
		GradeDeleteRequest.addProperty("traineeId", traineeId);
		rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_GRADE_ROUTING_KEY, GradeDeleteRequest.toString());
	}
	
	/**
	 * Create message for Panel to delete Panels associated with a trainee
	 *
	 * @param batchId
	 *
	 * @return 
	 */
	public void sendSimplePanelDeleteRequest(Integer traineeId) {
		JsonObject PanelDeleteRequest = new JsonObject();
		PanelDeleteRequest.addProperty("methodName", "delete");
		PanelDeleteRequest.addProperty("traineeId", traineeId);
		rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_PANEL_ROUTING_KEY, PanelDeleteRequest.toString());
	}
}