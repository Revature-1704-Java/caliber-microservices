package com.revature.caliber.repository;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.revature.caliber.model.SimpleBatch;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BatchRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	private BatchRepository batchRepo;

	@Test
	public void testFindAllByTrainerId() {
		List<SimpleBatch> test = batchRepo.findAllByTrainerId(1);
		assertFalse("Test Batch Not Empty", test.size()==0);
	}
	
	@Test
	public void testFindAll(){
		List<SimpleBatch> test = batchRepo.findAll();
		assertEquals(13,test.size());
		
	}
	
	@Test
	public void testFindOne() {
		SimpleBatch test = batchRepo.findOne(2100);
		assertEquals(test.getBatchId(), (Integer) 2100);
	}
}
