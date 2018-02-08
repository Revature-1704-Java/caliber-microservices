package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.TraineeRepository;
import com.revature.caliber.service.TraineeCompositionService;

@SpringBootApplication
@EnableEurekaClient 
public class TraineeRepositoryServiceApplication {
	@Autowired
	TraineeCompositionService tcs;
	
	@Autowired
	TraineeRepository tr;

	public static void main(String[] args) {
		SpringApplication.run(TraineeRepositoryServiceApplication.class, args);
	}
	
}
