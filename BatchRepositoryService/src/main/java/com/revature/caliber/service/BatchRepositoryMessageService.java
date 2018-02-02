package com.revature.caliber.service;

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
	public SimpleBatch receiveList(String message) {
		JsonObject request=getRequest(message);
		String method = request.get("methodName").getAsString();
		
		return null;
	}
	
	public JsonObject getRequest(String message){
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		return element.getAsJsonObject();
	}
}
