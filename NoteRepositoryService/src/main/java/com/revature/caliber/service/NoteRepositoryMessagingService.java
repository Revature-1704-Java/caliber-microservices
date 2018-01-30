package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NoteRepositoryMessagingService {
	
	@RabbitListener(queues = "caliber.queue")
	public String receive(String message) {
		System.out.println(message);
		return "Received from Consumer";
	}
}
