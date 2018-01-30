package com.revature.caliber;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.AssessmentType;
import com.revature.caliber.repository.AssessmentDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssessmentDAOTest {

	private static final Logger log = Logger.getLogger(AssessmentDAOTest.class);

	private long assessmentId = 2077;
	private short week = 5;
	private short batchId = 2150;

	@Autowired
	AssessmentDAO dao;

	Assessment test;

	@Before
	public void initialize() {
		log.info("Initalizing a Test Assessment for use in Tests");
		test = new Assessment();
		test.setTitle("TEST_ASSESSMENT");
		test.setBatchId(77);
		test.setRawScore(77);
		test.setType(AssessmentType.Other);
		test.setWeek(week);
		test.setCategoryId(77);
	}

	@Test
	public void testFindAll() {
		log.info("Getting All Assessments");
		List<Assessment> test = dao.findAll();
		assertFalse(test.isEmpty());
	}

	@Test
	public void testFindByAssessmentId() {
		log.info("Getting Assessment by assessmentId");
		Assessment test = dao.findByAssessmentId(assessmentId);
		assertFalse(test == null);
	}

	@Test
	public void testAddAssessment() {
		log.info("Adding Assessment");
		Assessment savedAssessment = dao.save(test);
		assertEquals(test.getAssessmentId(), savedAssessment.getAssessmentId());
	}

	@Test
	public void testUpdateAssessment() {
		log.info("Updating Assessment");
		Assessment savedAssessment = dao.save(test);
		savedAssessment.setTitle("UPDATED_ASSESSMENT");
		Assessment updatedAssessment = dao.save(savedAssessment);
		assertEquals(savedAssessment, updatedAssessment);
	}

	@Test
	public void testDeleteAssessment() {
		log.info("Deleting Assessment");
		Assessment savedTest = dao.save(test);
		dao.delete(savedTest);
		assertNull(dao.findByAssessmentId(savedTest.getAssessmentId()));
	}

	@Test
	public void findByWeekNumber() {
		log.info("Getting Assessment by Week Number");
		List<Assessment> assessments = dao.findDistinctByWeek(week);
		for (Assessment a : assessments) {
			if (a.getWeek() != week)
				Assert.fail("week Number does not match: " + a.toString());
		}
		assertFalse(assessments.isEmpty());
	}

	@Test
	public void findByBatchId() {
		log.info("Getting Assessment by batchId");
		List<Assessment> assessments = dao.findDistinctByBatchId(batchId);
		assertFalse(assessments.isEmpty());
	}

	@Test
	public void findByBatchIdAndWeek() {
		log.info("Getting Assessment by batchId and Week");
		List<Assessment> assessments = dao.findByBatchIdAndWeek(batchId, week);
		assertFalse(assessments.isEmpty());
	}
}
