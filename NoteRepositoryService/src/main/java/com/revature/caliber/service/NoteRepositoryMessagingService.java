package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleNote;

@Service
public class NoteRepositoryMessagingService {
	
	@Autowired
	private NoteRepositoryRequestDispatcher noteRepositoryRequestDispatcher;
	
//	@RabbitListener(queues = "revature.caliber.repos.note")
	@RabbitListener(queues = "caliber.note")
	public SimpleNote receiveSingleSimpleNoteRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return noteRepositoryRequestDispatcher.processSingleSimpleNoteRequest(request);
	}
	
//	@RabbitListener(queues = "revature.caliber.repos.note.list")
	@RabbitListener(queues = "caliber.note.list")
	public List<SimpleNote> receiveListSimpleNoteRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return noteRepositoryRequestDispatcher.processListSimpleNoteRequest(request);
	}
}
