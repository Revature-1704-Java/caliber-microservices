package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.service.BatchCompositionMessageService;
import com.revature.caliber.service.BatchCompositionService;

@SpringBootApplication
@EnableEurekaClient 
public class BatchRepositoryServiceApplication {
	@Autowired
	BatchCompositionMessageService bmcs;
	public static void main(String[] args) {
		SpringApplication.run(BatchRepositoryServiceApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner runner() {
//		return args -> {
//			System.out.println("here");
//			System.out.println(bmcs.sendListSimpleTraineeRequest(2100));
//			bmcs.sendSimpleTraineeDeleteRequest(2100);
//			System.out.println(bmcs.sendListSimpleTraineeRequest(2100));
//		};
//	}
}
