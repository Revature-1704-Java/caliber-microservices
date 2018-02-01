package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryMessagingService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@RabbitListener(queues = "caliber.grade.list")
	public List<SimpleGrade> receiveList(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		if(request.get("methodName").getAsString().equals("findGradesbyTrainee")) {
			List<SimpleGrade> grades = gradeRepository.findByTraineeId(request.get("traineeId").getAsInt());
			
			
			return grades;
		} 
		else if((request.get("methodName").getAsString().equals("findGradesbyAssessment"))){
			List<SimpleGrade> grades = gradeRepository.findByAssessmentId(request.get("traineeId").getAsLong());
			return grades;
		}
		else {
			return null;
		}
		
	}
	
	@RabbitListener(queues = "caliber.grade")
	public SimpleGrade receive(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		if(request.get("methodName").getAsString().equals("findGrade")) {
			SimpleGrade grade = gradeRepository.findOne(request.get("gradeId").getAsLong());
			return grade;
		}
		else {
			return null;
		}
	}
	
	
}
