package com.revature.caliber.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.model.Grade;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public interface GradeDAO extends JpaRepository<Grade, Integer>{
	

	/**
	 * Returns all grades for an assessment. 
	 * 
	 * @param assessmentId
	 * @return
	 */
	public List<Grade> findByAssessmentId(Long assessmentId);
	
	
	
	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findByTraineeId(Integer traineeId);
	
	
	
	
}
