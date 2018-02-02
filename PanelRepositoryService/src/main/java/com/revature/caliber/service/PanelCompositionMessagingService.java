package com.revature.caliber.service;

import java.awt.Panel;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleTrainee;


@Service
public class PanelCompositionMessagingService {
	
	@Autowired
    AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_PANEL_ROUTING_KEY = "B8ptbVDNyVB28mVA";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

    public SimpleTrainee sendSingleSimpleTraineeRequest(Integer traineeId) {
        JsonObject traineeRequest = new JsonObject();

        traineeRequest.addProperty("methodName", "findOne");
        traineeRequest.addProperty("traineeId", traineeId);
        
        return (SimpleTrainee) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_PANEL_ROUTING_KEY, traineeRequest.toString());
    }
}
