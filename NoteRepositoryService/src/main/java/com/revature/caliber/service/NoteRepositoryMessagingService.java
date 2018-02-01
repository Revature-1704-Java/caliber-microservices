package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public SimpleNote receiveSimpleRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		if(request.get("methodName").getAsString().equals("findTraineeNote")) {
			List<SimpleNote> notes = noteRepository.findByTraineeIdAndWeekAndQcFeedbackAndType(request.get("traineeId").getAsInt(), request.get("week").getAsShort(), false, NoteType.TRAINEE);
			SimpleNote note = null;
			if(notes.size() == 1)
				note = notes.get(0);
			return note;
		} else
			return null;
	}
	
	@RabbitListener(queues = "caliber.note.list")
	public List<SimpleNote> receiveListRequest(String message) {
		List<SimpleNote> notes = null;
		
		return notes;
	}
}
