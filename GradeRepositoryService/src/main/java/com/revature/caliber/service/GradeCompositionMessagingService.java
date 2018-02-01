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
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_GRADE_ROUTING_KEY = "aYF4wPtsGMjq72Lu";
	private static final String LIST_GRADE_ROUTING_KEY = "V6hbpnyZRH8ZQQ9e";
	
	
	public boolean sendSingleGradeRequest() {
	       return true;
	}
	
	public boolean sendListGradeRquest() {
		return true;
	}
}
