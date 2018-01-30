package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NoteRepositoryMessagingService {
	
	@RabbitListener(queues = "queue")
	public void receive(String message) {
		System.out.println(message);
	}
}
