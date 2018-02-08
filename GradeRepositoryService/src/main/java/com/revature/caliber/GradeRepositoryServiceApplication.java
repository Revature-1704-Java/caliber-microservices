package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.revature.caliber.repository.GradeRepository;

@SpringBootApplication
@EnableEurekaClient
public class GradeRepositoryServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(GradeRepositoryServiceApplication.class, args);
	}
}
