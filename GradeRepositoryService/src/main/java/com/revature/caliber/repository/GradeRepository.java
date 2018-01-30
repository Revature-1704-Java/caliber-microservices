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
public interface GradeRepository extends JpaRepository<Grade, Integer>{
	
	/**
	 * Returns grades for all trainees that took a particular assignment. Great
	 * for finding average/median/highest/lowest grades for a test
	 * 
	 * @param assessmentId
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Grade> findbyAssessmentId(Long assessmentId);
	
	
	
	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Grade> findbyTraineeId(Integer traineeId);
	
}
