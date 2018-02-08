package com.revature.caliber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaCleint
public class TrainerRepositoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrainerRepositoryServiceApplication.class, args);
	}
}
