package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	Note findByTraineeIdAndWeekAndQcFeedbackAndType(Integer traineeId, Short week, boolean qcFeedback, NoteType type);
	
	List<Note> findByBatchIdAndType(Integer batchId, NoteType type);
	List<Note> findByBatchIdAndWeekAndType(Integer batchId, Short week, NoteType type);
	List<Note> findByTraineeIdAndType(Integer traineeId, NoteType type);
	List<Note> findByBatchIdAndWeekAndQcFeedbackAndType(Integer batchId, Short week, boolean qcFeedback, NoteType type);
	
}
