package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.repository.GradeRepository;

public class GradeCompositionService {
	
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private GradeCompositionMessagingService gradeCompositionMessagingService;
	
	public Grade findOne(Long gradeId) {
		SimpleGrade basis = gradeRepository.findOne(gradeId);
		Grade result = composeGrade(basis);
		return null;
	}
	
	/**
	 * Returns absolutely all grades for only the most coarsely-grained
	 * reporting. Useful for feeding data into application for statistical
	 * analyses, such as regression analysis, calculating mean, and finding
	 * average ;)
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findAll(Long gradeId){
		List<SimpleGrade> basis = gradeRepository.findAll();
		List<Grade> result = composeListOfGrade(basis);
		return result;
	}
	
	
	/**
	 * Returns grades for all trainees that took a particular assignment. Great
	 * for finding average/median/highest/lowest grades for a test
	 * 
	 * @param assessmentId
	 * @return
	 */
	public List<Grade> findByAssessment(Long assessmentId) {
		List<SimpleGrade> basis = gradeRepository.findByAssessmentId(assessmentId);
		List<Grade> result = composeListOfGrade(basis);
		return result;
	}
	

	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findByTrainee(Integer traineeId) {
		List<SimpleGrade> basis = gradeRepository.findByTraineeId(traineeId);
		List<Grade> result = composeListOfGrade(basis);
		return result;
	}
	
	/**
	 * Returns all grades for a batch. Useful for calculating coarsely-grained
	 * data for reporting.
	 * 
	 * @param batchId
	 * @return
	 */
	public List<Grade> findByBatch(Integer batchId) {
		return null;
	}
	
	
	/**
	 * Returns all grades for a category. Useful for improving performance time
	 * of company-wide reporting
	 * 
	 * @param batchId
	 * @return
	 */
	public List<Grade> findByCategory(Integer categoryId) {
		return null;
	}
	
	/**
	 * Returns grades for all trainees in the batch on a given week. Used to
	 * load grade data onto the input spreadsheet, as well as tabular/chart
	 * reporting.
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	public List<Grade> findByWeek(Integer batchId, Integer week) {
		return null;
	}
	
	/**
	 * Returns all grades issued as acting trainer or cotrainer to a batch.
	 * Useful for calculating coarsely-grained data for reporting. Potential
	 * refactor here.. this queries database twice where we could find way to
	 * simply join.
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<Grade> findByTrainer(Integer trainerId) {
		return null;
	}
	
	private List<Grade> composeListOfGrade(List<SimpleGrade> src){
		List<Grade> dest = new LinkedList<Grade>();
		
		for(SimpleGrade curr : src) {
			Grade grade = new Grade(curr);
		}
		
		return dest;
	}
	
	private Grade composeGrade(SimpleGrade src) {
		SimpleTrainee simpleTrainee = gradeCompositionMessagingService.sendSimpleTraineeRequest(src.getTraineeId());
		SimpleAssessment simpleAssessment = gradeCompositionMessagingService.sendSimpleAssessmentRequest(src.getAssessmentId());
		
		Trainee trainee = new Trainee(simpleTrainee);
		Assessment assessment = new Assessment(simpleAssessment);
		
		Grade dest = new Grade(src);
		
		dest.setAssessment(assessment);
		dest.setTrainee(trainee);
		
		return dest;
			
	}
}
	