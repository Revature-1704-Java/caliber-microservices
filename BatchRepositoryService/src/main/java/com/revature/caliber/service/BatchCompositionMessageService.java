package com.revature.caliber.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainee;

@Service
public class BatchCompositionMessageService {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String LIST_TRAINEE_ROUTING_KEY = "eRQ7GaBRnHgGdV9D";
	private static final String LIST_NOTE_ROUTING_KEY = "cf22J9CGs8V95Rbm";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	public List<SimpleTrainee> sendListSimpleTraineeRequest(Integer batchId){
		JsonObject SimpleTraineeListRequest = new JsonObject();
		SimpleTraineeListRequest.addProperty("methodName", "findAllByBatchId");
		List<?> SimpleTraineeList=(List<?>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE,
				LIST_TRAINEE_ROUTING_KEY, SimpleTraineeListRequest.toString());
		return SimpleTraineeList.stream().map(x->(SimpleTrainee)x).collect(Collectors.toList());
	}
	
	public List<SimpleNote> sendListSimpleNoteRequest(Integer batchId){
		JsonObject SimpleNoteListRequest = new JsonObject();
		SimpleNoteListRequest.addProperty("methodName", "findAllByBatchId");
		List<?> SimpleNoteList = (List<?>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE,
				LIST_TRAINEE_ROUTING_KEY, SimpleNoteListRequest.toString());
		return SimpleNoteList.stream().map(x->(SimpleNote) x).collect(Collectors.toList());
	}
}
