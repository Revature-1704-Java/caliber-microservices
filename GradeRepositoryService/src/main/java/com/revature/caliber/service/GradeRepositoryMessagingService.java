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
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryMessagingService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@RabbitListener(queues = "caliber.grade.list")
	public List<Grade> receive(String message) {
		//System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		if(request.get("methodName").getAsString().equals("findGradesbyTraineeId")) {
			List<Grade> grades = gradeRepository.findByTraineeId(request.get("traineeId").getAsInt());
			return grades;
		} else {
			return null;
		}
		
	}
}
