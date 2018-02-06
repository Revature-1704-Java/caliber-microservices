package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.repository.TraineeRepository;

@Service
public class TraineeRepositoryRequestDispatcher {
	@Autowired
	private TraineeRepository traineeRepository;
	
	public SimpleTrainee processSingleSimpleTraineeRequest(JsonObject request) {
		SimpleTrainee result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Integer traineeId = request.get("traineeId").getAsInt();
			result = traineeRepository.findOne(traineeId);
		} else if(methodName.equals("delete")) {
			traineeRepository.delete(request.get("traineeId").getAsInt());
		}
		
		return result;
	}
	
	public List<SimpleTrainee> processListSimpleTraineeRequest(JsonObject request) {
		System.out.println("Hey hello");
		List<SimpleTrainee> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			System.out.println("find all");
			result = traineeRepository.findAll();
		} else if(methodName.equals("findAllByBatchId")){
			System.out.println("find batch");
			result = traineeRepository.findAllByBatchId(request.get("batchId").getAsInt());
		} else if(methodName.equals("delete")) {
			System.out.println("delete");
			result = traineeRepository.findAllByBatchId(request.get("batchId").getAsInt());
			for(SimpleTrainee t : result) {
				System.out.println(t);
				traineeRepository.delete(t.getTraineeId());
			}
			result = null;
		}
		
		System.out.println("return");
		
		return result;
	}
}