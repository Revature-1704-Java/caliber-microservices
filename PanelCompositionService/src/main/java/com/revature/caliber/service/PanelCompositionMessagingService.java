package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PanelCompositionMessagingService {
	@Autowired
    AmqpTemplate rabbitTemplate;
    
    public boolean send(String routingKey, String message) {
        Object response =  rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey, message);
        System.out.println(response.toString());
        return true;
    }
}
