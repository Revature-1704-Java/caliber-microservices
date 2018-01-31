package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleNote;

@Service
public class MockMessagingService {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public boolean send(String routingKey, String message) {
		JsonObject object = new JsonObject();
		
		object.addProperty("methodName", "findTraineeNote");
		object.addProperty("traineeId", 5529);
		object.addProperty("week", 2);
		
		SimpleNote response = (SimpleNote) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, object.toString());
		System.out.println(response);
		
		return true;
	}
}
