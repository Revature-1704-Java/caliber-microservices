package com.revature.caliber.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.repository.NoteRepository;

public class NoteCompositionService {
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteCompositionMessagingService noteCompositionMessagingService;
	
	public Note getNote(Integer noteId) {
		Note result = null;
		SimpleNote basis = noteRepository.findOne(noteId);
		
		boolean batch = noteCompositionMessagingService.sendSingleSimpleBatchRequest(basis.getBatchId());
		boolean trainee = noteCompositionMessagingService.sendSingleSimpleTraineeRequest(basis.getTraineeId());
		
		return result;
	}
}
