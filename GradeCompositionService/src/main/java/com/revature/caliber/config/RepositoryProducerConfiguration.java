package com.revature.caliber.config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.gson.JsonObject;

import com.revature.caliber.service.GradeCompositionMessagingService;

@Configuration
public class RepositoryProducerConfiguration {
		@Autowired
	   private GradeCompositionMessagingService mms;
	   
	   @Bean
	   public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
	       RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
	       rabbitTemplate.setExchange("revature.caliber.repos");
	       return new RabbitTemplate(factory);
	   }
	   
	   @Bean
	   public CommandLineRunner runner() {
	       return args -> {
	    	   JsonObject json = new JsonObject();
	    	   json.addProperty("methodName", "findGradesbyTrainee");
	    	   json.addProperty("traineeId", 1);
	    	   	
	    	   String jsonString = json.toString();
	   
	           mms.send("V6hbpnyZRH8ZQQ9e", jsonString);
	       };
	   }
}
