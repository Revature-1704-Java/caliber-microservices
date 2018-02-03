package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleTrainee;

@Service
public class TraineeRepositoryMessagingService {

	@Autowired
	private TraineeRepositoryRequestDispatcher traineeRepositoryRequestDispatcher;

	@RabbitListener(queues = "caliber.trainee")
	public SimpleTrainee receiveSingleSimpleTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processSingleSimpleTraineeRequest(request);
	}

	@RabbitListener(queues = "caliber.trainee.list")
	public List<SimpleTrainee> receiveListSimpleTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processListSimpleTraineeRequest(request);
	}
}
