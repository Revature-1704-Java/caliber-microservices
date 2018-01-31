package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.revature.caliber.model.Grade;

@RepositoryRestResource(collectionResourceRel = "grade", path = "grade")
public interface GradeRepository extends JpaRepository<Grade, Integer>{
	
	List<Grade> findAll();

	/**
	 * Returns all grades for an assessment. 
	 * 
	 * @param assessmentId
	 * @return
	 */
	List<Grade> findByAssessmentId(@Param("assessmentId") Long assessmentId);
	
	
	
	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findByTraineeId(@Param("traineeId")Integer traineeId);
	
	
	
	
	
	
}
