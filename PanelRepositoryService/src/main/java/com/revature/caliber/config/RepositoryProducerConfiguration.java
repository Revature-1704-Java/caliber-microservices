package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonObject;
import com.revature.caliber.service.PanelCompositionMessagingService;

@Configuration
public class RepositoryProducerConfiguration {

	@Autowired
    private PanelCompositionMessagingService mms;
    
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setExchange("revature.caliber.repos");
        return new RabbitTemplate(factory);
    }
    
    @Bean
    public CommandLineRunner runner() {
        return args -> {
        	JsonObject object = new JsonObject();
        	object.addProperty("methodName", "findOne");
        	object.addProperty("panelId", 1);

        	mms.send("B8ptbVDNyVB28mVA", object.toString());
        };
    }
}
