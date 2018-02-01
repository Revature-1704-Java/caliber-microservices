package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryRepositoryMessagingService {

	@RabbitListener(queues = "caliber.category")
	public void receive(String message) {
		System.out.println(message);
	}
}
