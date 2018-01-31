package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.repository.NoteRepository;

@Service
public class NoteRepositoryMessagingService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@RabbitListener(queues = "caliber.note")
	public SimpleNote receive(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		Gson gson = new Gson();
		if(request.get("methodName").getAsString().equals("findTraineeNote")) {
			SimpleNote note = noteRepository.findByTraineeIdAndWeekAndQcFeedbackAndType(request.get("traineeId").getAsInt(), request.get("week").getAsShort(), false, NoteType.TRAINEE);
			return note;
		} else
			return null;
	}
}
