package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PanelRepositoryMessagingService {
	
	@RabbitListener(queues = "caliber.panel")
    public void receive(String message) {
        System.out.println(message);
    }
}
