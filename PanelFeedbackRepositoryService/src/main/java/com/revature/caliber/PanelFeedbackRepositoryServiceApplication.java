package com.revature.caliber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PanelFeedbackRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelFeedbackRepositoryServiceApplication.class, args);
	}
}
