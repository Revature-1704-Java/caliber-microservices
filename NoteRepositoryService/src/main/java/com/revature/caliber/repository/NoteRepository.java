package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.SimpleNote;

@Repository
public interface NoteRepository extends JpaRepository<SimpleNote, Integer> {
	List<SimpleNote> findByBatchIdAndType(Integer batchId, NoteType type);
	List<SimpleNote> findByTraineeIdAndType(Integer traineeId, NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndType(Integer batchId, Short week, NoteType type);
	List<SimpleNote> findByTraineeIdAndWeekAndType(Integer traineeId, Short week, NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndQcFeedbackAndType(Integer batchId, Short week, boolean qcFeedback, NoteType type);
	List<SimpleNote> findByTraineeIdAndWeekAndQcFeedbackAndType(Integer traineeId, Short week, boolean qcFeedback, NoteType type);
	List<SimpleNote> findByTraineeIdAndTypeOrderByWeekAsc(Integer traineeId, NoteType type);
	List<SimpleNote> findByBatchIdAndQcFeedbackAndTypeOrderByWeekAsc(Integer batchId, boolean qcFeedback, NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndQcFeedbackAndTypeOrderByWeekAsc(Integer batchId, Short week, boolean qcFeedback, NoteType type);
	List<SimpleNote> findByTraineeIdAndQcFeedbackAndTypeOrderByWeekAsc(Integer traineeId, boolean qcFeedback, NoteType type);
}
