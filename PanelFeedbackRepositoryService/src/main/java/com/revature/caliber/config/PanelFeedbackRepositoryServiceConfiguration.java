package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonObject;
import com.revature.caliber.service.PanelFeedbackCompositionService;
import com.revature.caliber.service.PanelFeedbackRepositoryMessagingService;

@Configuration
public class PanelFeedbackRepositoryServiceConfiguration {

	@Autowired
	PanelFeedbackCompositionService panelFeedbackCompositionService;
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		return new RabbitTemplate(factory);
	}
	
	@Bean
	public PanelFeedbackCompositionService panelFeedbackCompositionService() {
		return new PanelFeedbackCompositionService();
	}
}
