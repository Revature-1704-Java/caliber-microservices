package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.services.Messenger;

@SpringBootApplication
@EnableEurekaClient
public class ReportingServiceApplication {
	
	@Autowired
	private Messenger m;

	public static void main(String[] args) {
		SpringApplication.run(ReportingServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			System.out.println("Test");
			System.out.println(m.findAllCurrentWithNotesAndTrainees());
		};
	}
}
