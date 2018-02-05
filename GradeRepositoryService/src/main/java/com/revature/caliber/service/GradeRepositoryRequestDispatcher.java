package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryRequestDispatcher {
	@Autowired
	private GradeRepository gradeRepository;
	
	public SimpleGrade processSingleSimpleGradeRequest(JsonObject request) {
		SimpleGrade result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Long gradeId = request.get("gradeId").getAsLong();
			result = gradeRepository.findOne(gradeId);
		}
		return result;
	}
	
	public List<SimpleGrade> processListSimpleGradeRequest(JsonObject request){
		List<SimpleGrade> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = gradeRepository.findAll();
		} else if(methodName.equals("findAllByTraineeId")) {
			result = gradeRepository.findByTraineeId(request.get("traineeId").getAsInt());
		} else if(methodName.equals("findAllByBatchId")) {
			result = gradeRepository.findByAssessmentId(request.get("batchId").getAsLong());
		}
		
		return result;
	}
	
	
}
