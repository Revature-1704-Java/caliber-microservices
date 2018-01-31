package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NoteCompositionMessagingService {
	@RabbitListener(queues = "caliber.note")
	public String receive(String message) {
		System.out.println(message);
		return "NoteCompositionMessagingService is ready";
	}
}
