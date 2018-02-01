package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.SimplePanelFeedback;

@Service
public class PanelFeedbackCompositionMessagingService {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public boolean send(String routingKey, String message) {
		SimplePanelFeedback response = (SimplePanelFeedback) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
		if (response != null) {
			System.out.println(response);
			return true;
		}
		System.out.println("Response NOT recieved");
		return false;
	}
}
