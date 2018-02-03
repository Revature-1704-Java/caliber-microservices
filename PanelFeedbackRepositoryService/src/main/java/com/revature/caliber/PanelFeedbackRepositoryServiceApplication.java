package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.JsonObject;
import com.revature.caliber.service.PanelFeedbackCompositionMessagingService;
import com.revature.caliber.service.PanelFeedbackCompositionService;
import com.revature.caliber.service.PanelFeedbackRepositoryMessagingService;

@SpringBootApplication
public class PanelFeedbackRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelFeedbackRepositoryServiceApplication.class, args);
	}
	
	@Autowired
	PanelFeedbackCompositionService panelFeedbackCompositionService;
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			
			System.out.println(panelFeedbackCompositionService.findOne(1L));
			
		};
	}
}
