package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.NoteType;

@Repository
public interface NoteRepository extends JpaRepository<SimpleNote, Integer> {
	SimpleNote findByTraineeIdAndWeekAndQcFeedbackAndType(Integer traineeId, Short week, boolean qcFeedback, NoteType type);
	
	List<SimpleNote> findByBatchIdAndType(Integer batchId, NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndType(Integer batchId, Short week, NoteType type);
	List<SimpleNote> findByTraineeIdAndType(Integer traineeId, NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndQcFeedbackAndType(Integer batchId, Short week, boolean qcFeedback, NoteType type);
	
}
