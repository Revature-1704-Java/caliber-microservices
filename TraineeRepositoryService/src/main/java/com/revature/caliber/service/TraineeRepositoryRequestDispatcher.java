package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.repository.TraineeRepository;

@Service
public class TraineeRepositoryRequestDispatcher {
	@Autowired
	private TraineeRepository traineeRepository;
	
	@Autowired
	private TraineeCompositionService traineeCompositionService;
	
	public SimpleTrainee processSingleSimpleTraineeRequest(JsonObject request) {
		SimpleTrainee result = null;
		String methodName = request.get("methodName").getAsString();
		Gson gson = new Gson();
		
		if(methodName.equals("findOne")) {
			Integer traineeId = request.get("traineeId").getAsInt();
			result = traineeRepository.findOne(traineeId);
		} if(methodName.equals("save")) {
			SimpleTrainee trainee = gson.fromJson(request.get("trainee").getAsString(), SimpleTrainee.class);
			result = traineeRepository.save(trainee);
		}
		
		return result;
	}
	
	public List<SimpleTrainee> processListSimpleTraineeRequest(JsonObject request) {
		List<SimpleTrainee> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = traineeRepository.findAll();
		}
		else if(methodName.equals("findAllByBatchId")){
			result = traineeRepository.findAllByBatchId(request.get("batchId").getAsInt());
		}
		
		return result;
	}

	public List<Trainee> processListTraineeRequest(JsonObject request) {
		List<Trainee> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAllByBatch")) {
			result = traineeCompositionService.findAllByBatch(request.get("batchId").getAsInt());
		}
		
		return result;
	}
}