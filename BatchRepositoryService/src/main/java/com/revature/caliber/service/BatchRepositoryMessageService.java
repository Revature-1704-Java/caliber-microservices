package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BatchRepositoryMessageService {
	@RabbitListener(queues = "caliber.batch")
	public String receive(String message) {
		return message;
	}
}
