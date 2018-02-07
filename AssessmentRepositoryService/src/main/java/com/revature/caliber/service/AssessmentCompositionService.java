package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.AssessmentRepository;

public class AssessmentCompositionService {
	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private AssessmentCompositionMessagingService assessmentCompositionMessagingService;

	public void save(Assessment assessment) {
	}

	public Assessment findOne(long id) {
		SimpleAssessment basis = assessmentRepository.findOne(id);
		Assessment result = composeAssessment(basis);

		return result;
	}

	public List<Assessment> findAll() {
		List<SimpleAssessment> basis = assessmentRepository.findAll();
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	public List<Assessment> findByWeek(Integer batchId, Short week) {
		List<SimpleAssessment> basis = assessmentRepository.findByBatchIdAndWeek(batchId, week);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	public List<Assessment> findByBatchId(Integer batchId) {
		List<SimpleAssessment> basis = assessmentRepository.findByBatchId(batchId);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	public List<Assessment> findByCategoryId(Integer categoryId) {
		List<SimpleAssessment> basis = assessmentRepository.findByCategoryId(categoryId);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	public void update(Assessment assessment) {
	}

	public void deleteByAssessmnetId(Long assessmentId) {
		assessmentRepository.deleteByAssessmentId(assessmentId);
	}
	
	public void deleteByBatchId(Integer batchId) {
		assessmentRepository.deleteByBatchId(batchId);
	}

	private List<Assessment> composeListOfAssessments(List<SimpleAssessment> simpleAssessments) {
		List<Assessment> assessments = new LinkedList<Assessment>();

		for (SimpleAssessment simpleAssessment : simpleAssessments) {
			Assessment assessment = composeAssessment(simpleAssessment);
			assessments.add(assessment);
		}

		return assessments;
	}

	private Assessment composeAssessment(SimpleAssessment simpleAssessment) {
		Assessment assessment = new Assessment(simpleAssessment);

		SimpleBatch simpleBatch = assessmentCompositionMessagingService
				.sendSingleSimpleBatchRequest(simpleAssessment.getBatchId());
		Batch batch = new Batch(simpleBatch);
		assessment.setBatch(batch);

		SimpleCategory simpleCategory = assessmentCompositionMessagingService
				.sendSingleSimpleCategoryRequest(simpleAssessment.getCategoryId());
		Category category = new Category(simpleCategory);
		assessment.setCategory(category);

		return assessment;
	}
}