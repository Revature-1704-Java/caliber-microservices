package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.repository.BatchRepository;

public class BatchRepositoryDispatcher {
	@Autowired
	BatchRepository repo;
	public SimpleBatch processSimpleBatchRequest(JsonObject request) {
		String methodName = request.get("methodName").getAsString();
		switch(methodName) {
			case "findOne":
				return repo.findOne(request.get("batchId").getAsInt());
			case "findOneWithDroppedTrainees":
				return repo.findOne(request.get("batchId").getAsInt());
			case "findOneWithTraineesAndGrades" :
				return repo.findOne(request.get("batchId").getAsInt());
			default:
				return null;
		}
	}
	public List<SimpleBatch> processListSimpleBatchRequest(JsonObject request) {
		String methodName = request.get("methodName").getAsString();
		if(methodName.contains("Current")){
			if(request.get("trainerId")==null) {
				return repo.findAllCurrent();
			}else{
				return repo.findAllCurrent(request.get("trainerId").getAsInt());
			}
		}else if(methodName.equals("findAllByTrainerId")){
			return repo.findAllByTrainerId(request.get("trainerId").getAsInt());
		}else{
			return repo.findAll();
		}
	}
}
