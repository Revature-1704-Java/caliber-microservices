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

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


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
