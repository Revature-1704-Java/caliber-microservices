package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.SimpleBatch;

@Service
public class BatchCompositionMessagingService {
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public SimpleBatch send(String msg) {
		return (SimpleBatch ) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", "XLNbCWqQzFHr9JfZ", msg);
	}
}
