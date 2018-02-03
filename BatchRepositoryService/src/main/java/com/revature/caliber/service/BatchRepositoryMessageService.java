package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleBatch;

@Service
public class BatchRepositoryMessageService {
	@Autowired
	BatchRepositoryDispatcher brd;
	@RabbitListener(queues = "revature.caliber.repos.batch")
	public SimpleBatch receive(String message) {
		System.out.println("message recieved 1");
		return brd.processSimpleBatchRequest(getRequest(message));
	}
	@RabbitListener(queues = "revature.caliber.repos.batch.list")
	public List<SimpleBatch> receiveList(String message) {
		System.out.println("message recieved");
		return brd.processListSimpleBatchRequest(getRequest(message));
	}
	
	public JsonObject getRequest(String message){
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		return element.getAsJsonObject();
	}
}
