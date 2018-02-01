package com.revature.caliber.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.repository.NoteRepository;

public class NoteCompositionService {
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteCompositionMessagingService noteCompositionMessagingService;
	
	public Note getNote(Integer noteId) {
		Note result = null;
		SimpleNote basis = noteRepository.findOne(noteId);
		SimpleBatch batch = noteCompositionMessagingService.sendSingleSimpleBatchRequest(basis.getBatchId());
		SimpleTrainee trainee = noteCompositionMessagingService.sendSingleSimpleTraineeRequest(basis.getTraineeId());
		
		System.out.println(batch);
		System.out.println(trainee);
		
		return result;
	}
}
