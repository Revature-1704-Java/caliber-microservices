package com.revature.caliber.test;

import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.Panel;
import com.revature.caliber.repository.PanelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PanelTest {

	@Autowired
	private PanelRepository panelRepository;
	/** Tests getting all panels */
	@Test
	public void findAllTest() {
		List<Panel> panels = panelRepository.findAll();
		System.out.println(panels);
		assertFalse(panels.isEmpty());
	}
	
	/** Tests getting all panels belonging to a trainee */
	@Test
	public void findByTraineeTraineeIdTest() {
		List<Panel> panels = panelRepository.findByTraineeId(5501);
		System.out.println(panels);
		assertFalse(panels.isEmpty());
	}
	
	/** Tests getting all repanels */
	@Test
	public void findAllRepanelsTest() {
		List<Panel> panels = panelRepository.findAllRepanels();
		System.out.println(panels);
		assertFalse(panels.isEmpty());		
	}
	
	/** Tests getting all panels within the last 14 days */
	@Test
	public void findRecentPanelsTest() {
		List<Panel> panels = panelRepository.findRecentPanels();
		System.out.println(panels);
		assertTrue(panels.isEmpty()); /* DATA SET HAS NO PANELS WITHIN LAST 14 DAYS */
	}

	/** Tests getting one panel by panel Id */
	@Test
	public void findOneTest() {
		Panel panel = panelRepository.findOne(5);
		System.out.println(panel);
		assertFalse(panel == null);
	}
	
	/** Tests deleting a panel by panel Id */
	@Test
	public void deleteTest() {
		Panel panel = panelRepository.delete(5);
		System.out.println(panel);
		assertTrue(panel == null); 
	}
}
