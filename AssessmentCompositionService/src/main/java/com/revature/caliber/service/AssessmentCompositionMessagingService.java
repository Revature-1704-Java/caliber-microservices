package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AssessmentCompositionMessagingService {
	@RabbitListener(queues = "caliber.assessment")
	public String receive(String message) {
		System.out.println(message);
		return "AssessmentCompositionMessagingService is ready";
	}
}
