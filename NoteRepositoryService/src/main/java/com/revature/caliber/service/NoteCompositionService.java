package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.NoteRepository;

public class NoteCompositionService {
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteCompositionMessagingService noteCompositionMessagingService;
	
	public Note findOne(Integer noteId) {
		SimpleNote basis = noteRepository.findOne(noteId);
		Note result = composeNote(basis);
		
		return result;
	}
	
	public List<Note> findBatchNotes(Integer batchId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndWeekAndQcFeedbackAndType(batchId, week, false, NoteType.BATCH);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findIndividualNotes(Integer batchId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndWeekAndQcFeedbackAndType(batchId, week, false, NoteType.TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public Note findTraineeNote(Integer traineeId, Short week) {
		SimpleNote basis = noteRepository.findOneByTraineeIdAndWeekAndQcFeedbackAndType(traineeId, week, false, NoteType.TRAINEE);
		Note result = composeNote(basis);
		
		return result;
	}
	
	public Note findQCTraineeNote(Integer traineeId, Short week) {
		SimpleNote basis = noteRepository.findOneByTraineeIdAndWeekAndQcFeedbackAndType(traineeId, week, true, NoteType.QC_TRAINEE);
		Note result = composeNote(basis);
		
		return result;
	}
	
	public Note findQCBatchNotes(Integer batchId, Short week) {
		SimpleNote basis = noteRepository.findOneByBatchIdAndWeekAndQcFeedbackAndType(batchId, week, true, NoteType.QC_BATCH);
		Note result = composeNote(basis);
		
		return result;
	}
	
	public List<Note> findAllBatchQCNotes(Integer batchId) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndType(batchId, NoteType.QC_BATCH);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findQCIndividualNotes(Integer traineeId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByTraineeIdAndWeekAndQcFeedbackAndType(traineeId, week, true, NoteType.QC_TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllBatchNotes(Integer batchId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndWeekAndType(batchId, week, NoteType.BATCH);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllIndividualNotes(Integer traineeId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByTraineeIdAndWeekAndType(traineeId, week, NoteType.TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllPublicIndividualNotes(Integer traineeId) {
		List<SimpleNote> basis = noteRepository.findAllByTraineeIdAndTypeOrderByWeekAsc(traineeId, NoteType.TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllQCBatchNotes(Integer batchId) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndQcFeedbackAndTypeOrderByWeekAsc(batchId, true, NoteType.QC_BATCH);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllQCTraineeNotes(Integer batchId, Short week) {
		List<SimpleNote> basis = noteRepository.findAllByBatchIdAndWeekAndQcFeedbackAndTypeOrderByWeekAsc(batchId, week, true, NoteType.QC_TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	public List<Note> findAllQCTraineeOverallNotes(Integer traineeId) {
		List<SimpleNote> basis = noteRepository.findAllByTraineeIdAndQcFeedbackAndTypeOrderByWeekAsc(traineeId, true, NoteType.QC_TRAINEE);
		List<Note> result = composeListOfNotes(basis, false);
		
		return result;
	}
	
	private List<Note> composeListOfNotes(List<SimpleNote> src, boolean includeDropped) {
		List<Note> dest = new LinkedList<Note>();
		
		for(SimpleNote curr : src) {
			Note note = new Note(curr);
			
			if(!includeDropped && note.getTrainee().getTrainingStatus() != TrainingStatus.Dropped)
				dest.add(new Note(curr));
			else if(includeDropped)
				dest.add(new Note(curr));
		}
		
		return dest;
	}
	
	private Note composeNote(SimpleNote src) {
		SimpleBatch simpleBatch = noteCompositionMessagingService.sendSingleSimpleBatchRequest(src.getBatchId());
		SimpleTrainee simpleTrainee = noteCompositionMessagingService.sendSingleSimpleTraineeRequest(src.getTraineeId());
		Batch batch = new Batch(simpleBatch);
		Trainee trainee = new Trainee(simpleTrainee);
		Note dest = new Note(src);
		
		trainee.setBatch(batch);
		dest.setBatch(batch);
		dest.setTrainee(trainee);
		
		return dest;
	}
}
