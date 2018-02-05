package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Category;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.CategoryRepository;
import com.revature.caliber.test.unit.CategoryRepositoryTest;


public class CategoryCompositionService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryCompositionMessagingService categoryCompositionMessagingService;
	private static final Logger log = Logger.getLogger(CategoryCompositionService.class);
	
	//findOne
	public Category findOne(int categoryId) {
		log.info("Finding one simple category");
		SimpleCategory basis = categoryRepository.findOne(categoryId);
		Category result = composeCategory(basis);
		return result;
	}
	
	//findAll
	public List<Category> findAll() {
		List<SimpleCategory> basis = categoryRepository.findAllByOrderBySkillCategoryAsc();
		List<Category> result = composeListOfCategory(basis);
		
		return result;
	}
	
	//findAllActive
	public List<Category> findAllActive() {
		List<SimpleCategory> basis = categoryRepository.findByActiveOrderByCategoryIdAsc(true);
		List<Category> result = composeListOfCategory(basis);
		
		return result;
	}
	
	/*
	//save
	public ? save(PanelFeedback panelFeedback) {
		
	}
	
	//update
	public ? update(PanelFeedback panelFeedback) {
		
	}
	
	//delete
	public ? delete(Long panelFeedbackd) {
		
	}
	*/
	
	private List<Category> composeListOfCategory(List<SimpleCategory> src) {
		List<Category> dest = new LinkedList<Category>();
		
		for(SimpleCategory curr : src) {
			dest.add(new Category(curr));
		}
		
		return dest;
	}
	
	private Category composeCategory(SimpleCategory src) {
		Category dest = new Category(src);
		return dest;
	}
}
