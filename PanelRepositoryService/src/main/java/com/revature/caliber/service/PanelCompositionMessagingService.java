package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.SimpleTrainer;

@Service
public class PanelCompositionMessagingService {

	@Autowired
	AmqpTemplate rabbitTemplate;

	private static final String SINGLE_TRAINEE_ROUTING_KEY = "JyoH3uRmktGn9MnW";
	private static final String SINGLE_TRAINER_ROUTING_KEY = "9xdaX72tPYuz8xDP";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	public SimpleTrainee sendSingleSimpleTraineeRequest(Integer traineeId) {
		JsonObject traineeRequest = new JsonObject();

		traineeRequest.addProperty("methodName", "findOne");
		traineeRequest.addProperty("traineeId", traineeId);

		return (SimpleTrainee) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINEE_ROUTING_KEY,
				traineeRequest.toString());
	}

	public SimpleTrainer sendSingleSimpleTrainerRequest(Integer trainerId) {
		JsonObject trainerRequest = new JsonObject();

		trainerRequest.addProperty("methodName", "findOne");
		trainerRequest.addProperty("trainerId", trainerId);

		return (SimpleTrainer) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINER_ROUTING_KEY,
				trainerRequest.toString());
	}
	
}
