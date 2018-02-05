package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleTrainer;


@Service
public class TrainerRepositoryMessagingService {
	
	@Autowired
	private TrainerRepositoryRequestDispatcher trainerRepositoryRequestDispatcher;
	
	
	@RabbitListener(queues = "revature.caliber.repos.trainer")
	public SimpleTrainer receiveSingleSimpleTrainerRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return trainerRepositoryRequestDispatcher.processSingleSimpleTrainerRequest(request);
	}
	
	@RabbitListener(queues = "revature.caliber.repos.trainer.list")
	public List<SimpleTrainer> receiveListSimpleTrainerRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return trainerRepositoryRequestDispatcher.processListSimpleTrainerRequest(request);
	}
	
}
