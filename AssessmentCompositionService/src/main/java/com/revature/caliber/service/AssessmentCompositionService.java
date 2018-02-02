package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.caliber.model.SimpleAssessment;

@RestController
@RequestMapping(value = "/trainer")
public class AssessmentCompositionService {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "assessment", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SimpleAssessment> findAssessments() {
		return null;
	}

	@GetMapping(value = "assessment/id/{assessmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SimpleAssessment findAssessment(@PathVariable("assessmentId") long assessmentId) {
		SimpleAssessment simpleTraineeAssessment = restTemplate.getForObject(
				"http://localhost:8181/assessment/search/findByAssessmentId?assessmentId=" + assessmentId,
				SimpleAssessment.class);

		return simpleTraineeAssessment;
	}

	@GetMapping(value = "assessment/week/{week}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<SimpleAssessment> findDistinctByWeek(@Param("week") Short week) {
		return null;
	}

	@GetMapping(value = "assessment/batch/{batchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<SimpleAssessment> findDistinctByBatchId(@Param("batchId") Integer batchId) {
		return null;
	}

	@GetMapping(value = "assessment/batch_week/{batchId}/{week}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<SimpleAssessment> findByBatchIdAndWeek(@Param("batchId") Integer batchId, @Param("week") Short week) {
		return null;
	}
}
