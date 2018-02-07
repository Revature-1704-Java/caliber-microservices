package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryMessagingService {
	
	@Autowired
	private GradeRepositoryRequestDispatcher gradeRepositoryRequestDispatcher;
	
	@RabbitListener(queues = "revature.caliber.repos.grade.list")
	public List<SimpleGrade> receiveList(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processListSimpleGradeRequest(request);

	}
	
	@RabbitListener(queues = "revature.caliber.repos.grade")
	public SimpleGrade receive(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processSingleSimpleGradeRequest(request);
	}
	
	
	@RabbitListener(queues = "revature.caliber.service.grade.list")
	public List<Grade> recieveBatchId(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processListComplexGradeRequest(request);
	}
	
	@RabbitListener(queues = "revature.caliber.dto.grade")
	public Grade receiveGradeDTORequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processGradeDTORequest(request);
	}
}
