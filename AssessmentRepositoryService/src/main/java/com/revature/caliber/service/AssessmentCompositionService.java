package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.AssessmentRepository;

@Service
public class AssessmentCompositionService {
	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private AssessmentCompositionMessagingService assessmentCompositionMessagingService;

	public SimpleAssessment save(Assessment assessment) {
		SimpleAssessment simpleAssessment = new SimpleAssessment(assessment);

		return assessmentRepository.save(simpleAssessment);
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

	public List<Assessment> findByWeek(Short week) {
		List<SimpleAssessment> basis = assessmentRepository.findByWeek(week);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	public List<Assessment> findByBatchIdAndWeek(Integer batchId, Short week) {
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

	public SimpleAssessment update(Assessment assessment) {
		SimpleAssessment simpleAssessment = new SimpleAssessment(assessment);

		return assessmentRepository.save(simpleAssessment);
	}

	public void deleteByAssessmentId(Long assessmentId) {
		assessmentCompositionMessagingService.sendGradeDeleteRequestForAssessmentId(assessmentId);
		assessmentRepository.deleteByAssessmentId(assessmentId);
	}

	public void deleteByBatchId(Integer batchId) {
		for (SimpleAssessment sa : assessmentRepository.findByBatchId(batchId)) {
			assessmentCompositionMessagingService.sendGradeDeleteRequestForAssessmentId(sa.getAssessmentId());
		}
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

	public void createAssessmentFromDTO(String category, String batchResourceId) {
		SimpleBatch simpleBatch = assessmentCompositionMessagingService.sendSingleSimpleBatchRequest(batchResourceId);
		SimpleCategory simpleCategory = assessmentCompositionMessagingService.sendSingleSimpleCategoryRequest(category);
		SimpleAssessment simpleAssessment = new SimpleAssessment();

		simpleAssessment.setBatchId(simpleBatch.getBatchId());
		simpleAssessment.setCategoryId(simpleCategory.getCategoryId());

		assessmentRepository.save(simpleAssessment);
	}
}