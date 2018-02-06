package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.caliber.service.PanelFeedbackCompositionService;

@SpringBootApplication
public class PanelFeedbackRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelFeedbackRepositoryServiceApplication.class, args);
	}

	@Autowired
	PanelFeedbackCompositionService panelFeedbackCompositionService;
}
