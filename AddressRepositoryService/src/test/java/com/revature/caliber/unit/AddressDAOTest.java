package com.revature.caliber.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.Address;
import com.revature.caliber.repository.AddressRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressDAOTest {
	
	@Autowired
	AddressRepository dao;
	
	@Test
	public void testFindAll() {
		List<Address> test = dao.findAll();
		
		assertFalse(test.isEmpty());
	}
	
	@Test
	public void testFindByAddressId() {
		Address test = dao.findOne(1);
		assertNotNull(test);
	}
	
	@Test
	public void testAddAddress() {
		Address test = new Address();
		
		test.setStreet("test");
		test.setCity("test");
		test.setState("test");
		test.setZipcode("test");
		test.setCompany("test");
		test.setActive(false);
		
		Address testcheck = dao.save(test);
		
		assertEquals(test.getZipcode(), testcheck.getZipcode());
	}
	
	@Test
	public void testUpdateAddress() {
		Address test = new Address();
		
		test.setStreet("thislane");
		test.setCity("thisplace");
		test.setState("test");
		test.setZipcode("test");
		test.setCompany("test");
		test.setActive(false);
		
		test = dao.save(test);
		
		System.out.println(test);
		
		test = dao.findOne(1);
		
		test.setStreet("test");
		test.setCity("test");
		test.setState("test");
		test.setZipcode("test");
		test.setCompany("test");
		test.setActive(false);
		
		dao.save(test);
		
		//assertEquals(test, dao.findByAddressId(0));
	}
	

	@Test
	public void testDeleteAddress() {
		Address test = new Address();
		
		test.setStreet("test");
		test.setCity("test");
		test.setState("test");
		test.setZipcode("test");
		test.setCompany("test");
		test.setActive(false);
		
		Address tester = dao.save(test);
		dao.delete(tester);
		
		assertNull(dao.findOne(tester.getAddressId()));
	}

}
