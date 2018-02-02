package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AssessmentRepositoryMessagingService {
	
	@RabbitListener(queues = "caliber.assessment")
	public String receive(String message) {
//		JsonParser parser = new JsonParser();
//		JsonElement element = parser.parse(message);
		System.out.println("Recieved from caliber.assessment" + message);
		return "Received from Consumer";
	}
}
