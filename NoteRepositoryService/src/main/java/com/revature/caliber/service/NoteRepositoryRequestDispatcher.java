package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.repository.NoteRepository;

@Service
public class NoteRepositoryRequestDispatcher {
	@Autowired
	private NoteRepository noteRepository;
	
	public SimpleNote processSingleSimpleNoteRequest(JsonObject request) {
		SimpleNote result = null;
		String methodName = request.get("methodName").getAsString();
		Gson gson = new Gson();
		
		if(methodName.equals("findOne")) {
			result = noteRepository.findOne(request.get("noteId").getAsInt());
		} else if(methodName.equals("save")) {
			result = noteRepository.save(gson.fromJson(request.get("note").getAsString(), SimpleNote.class));
		}
		
		return result;
	}
	
	public List<SimpleNote> processListSimpleNoteRequest(JsonObject request) {
		List<SimpleNote> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = noteRepository.findAll();
		} else if(methodName.equals("findAllByTraineeId")) {
			result = noteRepository.findAllByTraineeId(request.get("traineeId").getAsInt());
		} else if(methodName.equals("findAllByBatchId")) {
			result = noteRepository.findAllByBatchId(request.get("batchId").getAsInt());
		}
		
		return result;
	}
}
