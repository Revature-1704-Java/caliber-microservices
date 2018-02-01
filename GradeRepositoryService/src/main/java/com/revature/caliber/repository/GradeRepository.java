package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.revature.caliber.model.SimpleGrade;

@RepositoryRestResource(collectionResourceRel = "grade", path = "grade")
public interface GradeRepository extends JpaRepository<SimpleGrade, Long>{
	
	List<SimpleGrade> findAll();

	/**
	 * Returns all grades for an assessment. 
	 * 
	 * @param assessmentId
	 * @return
	 */
	public List<SimpleGrade> findByAssessmentId(@Param("assessmentId") Long assessmentId);
	
	
	
	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<SimpleGrade> findByTraineeId(@Param("traineeId")Integer traineeId);
	
	
	
	
	
	
}
