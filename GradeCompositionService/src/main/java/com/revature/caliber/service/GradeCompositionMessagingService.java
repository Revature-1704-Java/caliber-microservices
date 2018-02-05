package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeCompositionMessagingService {
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public boolean send(String routingKey, String message) {
			System.out.println(message);
	       List<String> response = (ArrayList<String>) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
	       System.out.println(response);
	       for(int i = 0; i < response.size(); i++) {
	    	   System.out.println(response.get(i));
	       }
	       return true;
	}
}
