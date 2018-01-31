package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.SimpleNote;

@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends JpaRepository<SimpleNote, Integer> {
	SimpleNote findByTraineeIdAndWeekAndQcFeedbackAndType(@Param("traineeId") Integer traineeId, @Param("week") Short week, @Param("qcFeedback") boolean qcFeedback, @Param("type") NoteType type);
	
	List<SimpleNote> findByBatchIdAndType(@Param("batchId") Integer batchId, @Param("type") NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndType(@Param("batchId") Integer batchId, @Param("week") Short week, @Param("type") NoteType type);
	List<SimpleNote> findByTraineeIdAndType(@Param("traineeId") Integer traineeId, @Param("type") NoteType type);
	List<SimpleNote> findByBatchIdAndWeekAndQcFeedbackAndType(@Param("batchId") Integer batchId, @Param("week") Short week, @Param("qcFeedback") boolean qcFeedback, @Param("type") NoteType type);
	
}
