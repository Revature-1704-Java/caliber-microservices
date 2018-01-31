package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanelFeedbackCompositionMessagingService {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public boolean send(String routingKey, String message) {
		String response = (String) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
		System.out.println(response);
		return true;
	}
}
