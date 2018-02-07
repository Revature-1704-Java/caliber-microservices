package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.service.AssessmentCompositionService;

@Configuration
public class AssessmentCompositionServiceConfiguration {
	@Autowired
	AssessmentCompositionService assessmentCompositionService;

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		return new RabbitTemplate(factory);
	}

	@Bean
	public AssessmentCompositionService assessmentCompositionService() {
		return new AssessmentCompositionService();
	}

//	@Bean
//	public CommandLineRunner runner() {
//		return args -> {
//			Assessment assessment = assessmentCompositionService.findOne((long) 5175);
//
//			System.out.println(assessment);
//		};
//	}
}