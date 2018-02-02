package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonObject;
import com.revature.caliber.service.PanelFeedbackRepositoryMessagingService;

@Configuration
public class RepositoryProducerConfiguration {

	@Autowired
	private PanelFeedbackRepositoryMessagingService mms;
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setExchange("revature.caliber.repos");
		return new RabbitTemplate(factory);
	}
	
	/*
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			//findOne test
			JsonObject object = new JsonObject();
			object.addProperty("methodName", "findOne");
			object.addProperty("panelFeedbackId", 1);
			
			//findAll test
			JsonObject object2 = new JsonObject();
			object2.addProperty("methodName", "findAll");
			
			mms.send("4jZ2GMxLP7VyQPBn", object.toString()); //single 
			//mms.send("5MKVoktka2jXh9yR", object.toString());              //Lists
		};
	}
	*/
}
