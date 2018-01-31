package com.revature.caliber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.repository.AssessmentDAO;

@SpringBootApplication
public class AssessmentRepositoryServiceApplication {
	
	@Autowired
	AssessmentDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentRepositoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			List<SimpleAssessment> assessments = dao.findAll();
			
			System.out.println(assessments);
		};
	}
}
