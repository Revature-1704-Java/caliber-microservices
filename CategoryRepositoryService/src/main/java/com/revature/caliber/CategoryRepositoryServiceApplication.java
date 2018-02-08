package com.revature.caliber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CategoryRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryRepositoryServiceApplication.class, args);
	}
}
