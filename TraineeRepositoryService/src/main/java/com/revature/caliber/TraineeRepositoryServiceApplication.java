package com.revature.caliber;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TraineeRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeRepositoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			System.out.println("Started");
		};
	}
}
