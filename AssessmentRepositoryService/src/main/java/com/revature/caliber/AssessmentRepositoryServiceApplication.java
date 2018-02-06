package com.revature.caliber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.caliber.repository.AssessmentDAO;

@SpringBootApplication
public class AssessmentRepositoryServiceApplication {
	
	@Autowired
	AssessmentDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentRepositoryServiceApplication.class, args);
	}
}
