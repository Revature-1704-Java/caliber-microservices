package com.revature.caliber.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Address;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainer;

@Service
public class BatchCompositionMessageService {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String LIST_TRAINEE_ROUTING_KEY = "eRQ7GaBRnHgGdV9D";
	private static final String LIST_NOTE_ROUTING_KEY = "cf22J9CGs8V95Rbm";
	private static final String TRAINER_ROUTING_KEY = "9xdaX72tPYuz8xDP";
	private static final String ADDRESS_ROUTING_KEY = "oEKQeav8rcejeYgq";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	public SimpleTrainer sendSimpleTrainerRequest(Integer trainerId) {
		JsonObject SimpleTrainerRequest = new JsonObject();
		SimpleTrainerRequest.addProperty("methodName", "findOne");
		SimpleTrainerRequest.addProperty("trainerId", trainerId);
		return (SimpleTrainer) rabbitTemplate.convertSendAndReceive(
				RABBIT_REPO_EXCHANGE, TRAINER_ROUTING_KEY, SimpleTrainerRequest.toString());
		
	}
	public Address sendSimpleAddressRequest(Integer addressId) {
		JsonObject SimpleAddressRequest = new JsonObject();
		SimpleAddressRequest.addProperty("methodName", "findOne");
		SimpleAddressRequest.addProperty("addressId", addressId);
		return (Address) rabbitTemplate.convertSendAndReceive(
				RABBIT_REPO_EXCHANGE, ADDRESS_ROUTING_KEY, SimpleAddressRequest.toString());
		
	}
	public List<SimpleTrainee> sendListSimpleTraineeRequest(Integer batchId){
		JsonObject SimpleTraineeListRequest = new JsonObject();
		SimpleTraineeListRequest.addProperty("methodName", "findAllByBatchId");
		SimpleTraineeListRequest.addProperty("batchId", batchId);
		List<?> SimpleTraineeList=(List<?>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE,
				LIST_TRAINEE_ROUTING_KEY, SimpleTraineeListRequest.toString());
		return SimpleTraineeList.stream().map(x->(SimpleTrainee)x).collect(Collectors.toList());
	}
	
	public List<SimpleNote> sendListSimpleNoteRequest(Integer batchId){
		JsonObject SimpleNoteListRequest = new JsonObject();
		SimpleNoteListRequest.addProperty("methodName", "findAllByBatchId");
		SimpleNoteListRequest.addProperty("batchId", batchId);
		List<?> SimpleNoteList = (List<?>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE,
				LIST_NOTE_ROUTING_KEY, SimpleNoteListRequest.toString());
		return SimpleNoteList.stream().map(x->(SimpleNote) x).collect(Collectors.toList());
	}
}
