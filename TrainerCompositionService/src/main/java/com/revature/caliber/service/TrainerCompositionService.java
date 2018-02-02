package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
//import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainer;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerCompositionService {
	
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public boolean send(String routingKey, String message) {
		JsonObject object = new JsonObject();
		
		object.addProperty("methodName", "findByTrainerId");
		//object.addProperty("methodName", "findByEmail");
		//object.addProperty("methodName", "findByBatchId");
		object.addProperty("id", 17);
		object.addProperty("email", "test@test.com");
		//object.addProperty("id", 17);
		
		SimpleTrainer response = (SimpleTrainer) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, object.toString());
		System.out.println(response);
		
		return true;
	}
	
	
	/*
	@GetMapping(value = "/{trainerId}")
	public SimpleTrainer findSimpleTrainer(@PathVariable("trainerId") int trainerId) {
		SimpleTrainer simpletrainer = restTemplate.getForObject("http://localhost:8180/trainer/findByTrainerId?trainerId=" + trainerId, SimpleTrainer.class);
		
		return simpletrainer;
	}
	*/
}
