package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.repository.AssessmentRepository;

@Service
public class AssessmentRepositoryRequestDispatcher {
	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private AssessmentCompositionService assessmentCompositionService;
	
	public SimpleAssessment processSingleSimpleAssessmentRequest(JsonObject request) {
		SimpleAssessment result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Long assessmentId = request.get("assessmentId").getAsLong();
			result = assessmentRepository.findOne(assessmentId);
		}
		
		return result;
	}
	
	public List<SimpleAssessment> processListSimpleAssessmentRequest(JsonObject request) {
		List<SimpleAssessment> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = assessmentRepository.findAll();
		}
		else if (methodName.equals("findByWeek")) {
			Integer batchId = request.get("batchId").getAsInt();
			Short week = request.get("week").getAsShort();
			result = assessmentRepository.findByBatchIdAndWeek(batchId, week);
		}
		
		return result;
	}
	
	public Assessment processAssessmentDTORequest(JsonObject request) {
		Assessment result = null;
		
		assessmentCompositionService.createAssessmentFromDTO(request.get("category").getAsString(), request.get("batch").getAsString());
		
		return result;
	}
}