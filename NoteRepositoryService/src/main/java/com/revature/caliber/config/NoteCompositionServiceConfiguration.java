package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.caliber.service.NoteCompositionService;

@Configuration
public class NoteCompositionServiceConfiguration {
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		return new RabbitTemplate(factory);
	}

}
