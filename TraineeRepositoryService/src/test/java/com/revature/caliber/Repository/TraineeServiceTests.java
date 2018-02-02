package com.revature.caliber.Repository;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.model.Trainee;
import com.revature.repository.TraineeRepository;

import static org.junit.Assert.*;
import org.junit.Test;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TraineeServiceTests {
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	TraineeRepository dao;
	@Test
	public void test() {
		List<Trainee> listOfTrainees = dao.findAllByBatchIdWhereTrainingstatusNotEqualDropped(1);
		assertFalse(listOfTrainees.isEmpty());
		System.out.println(listOfTrainees.get(1));
		listOfTrainees.forEach(x->System.out.println(x.getTrainingStatus()));
		
		
	}
	
}
