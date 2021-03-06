package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonObject;
import com.revature.caliber.service.CategoryCompositionService;
import com.revature.caliber.service.CategoryRepositoryMessagingService;

@Configuration
public class RepositoryProducerConfiguration {
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		return new RabbitTemplate(factory);
	}
	
	@Bean
	public CategoryCompositionService panelFeedbackCompositionService() {
		return new CategoryCompositionService();
	}
}
