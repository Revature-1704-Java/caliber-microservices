package com.revature.caliber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
			
			SimpleAssessment assessment = assessments.get(0);
			Gson gson = new Gson();
			String gsonObject = gson.toJson(assessment);
			System.out.println(gsonObject);
			
			JsonParser jp = new JsonParser();
			JsonElement element = jp.parse(gsonObject);
			System.out.println(element);
			
			JsonObject request = element.getAsJsonObject();
			System.out.println(request);
		};
	}
}
