package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.repository.TrainerRepository;


@Service
public class TrainerRepositoryMessagingService {
	
	@Autowired
	private TrainerRepository trainerDao;
	
	@RabbitListener(queues = "caliber.trainer")
	public SimpleTrainer receive(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		
		//DEBUGGING
		System.out.println(message);
		
		
		if(request.get("methodName").getAsString().equals("findByTrainerId")) {
			SimpleTrainer trainer = trainerDao.findByTrainerId(request.get("id").getAsInt());
			return trainer;
		} else
			return null;
	}
}
