package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class NoteRepositoryMessagingService {
	
	@RabbitListener(queues = "caliber.note")
	public String receive(String message) {
//		JsonParser parser = new JsonParser();
//		JsonElement element = parser.parse(message);
		System.out.println("Received from caliber.note: " + message);
		return "Hello back!";
	}
}
