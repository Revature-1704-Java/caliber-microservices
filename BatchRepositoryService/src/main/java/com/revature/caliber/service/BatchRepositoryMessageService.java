package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.repository.BatchRepository;

@Service
public class BatchRepositoryMessageService {
	@Autowired
	BatchRepository repo;
	
	@RabbitListener(queues = "caliber.batch")
	public SimpleBatch receive(String message) {
		System.out.println(message);
		JsonObject request=getRequest(message);
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
	@RabbitListener(queues = "caliber.batch.list")
	public List<SimpleBatch> receiveList(String message) {
		JsonObject request=getRequest(message);
		String methodName = request.get("methodName").getAsString();
		if(methodName.contains("Current")){
			if(request.get("trainerId")==null) {
				return repo.findAllCurrent();
			}else{
				return repo.findAllCurrent(request.get("trainerId").getAsInt());
			}
		}else{
			return repo.findAll();
		}
	}
	
	public JsonObject getRequest(String message){
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		return element.getAsJsonObject();
	}
}
