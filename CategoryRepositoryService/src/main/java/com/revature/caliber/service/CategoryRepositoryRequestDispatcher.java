package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.CategoryRepository;

/**
 * Processes messages from other services.
 * FindOne and findAll are requests from other services needed
 * to construct their complex beans for the front end.
 * 
 */
@Service
public class CategoryRepositoryRequestDispatcher {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public SimpleCategory processSingleSimpleCategoryRequest(JsonObject request) {
		SimpleCategory result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			int categoryId = request.get("categoryId").getAsInt();
			result = categoryRepository.findOne(categoryId);
		}
		else if(methodName.equals("findOneBySkillCategory")) {
			String skillCategory = request.get("skillCategory").getAsString();
			result = categoryRepository.findOneBySkillCategory(skillCategory);
		}
		
		return result;
	}
	
	public List<SimpleCategory> processListSimpleCategoryRequest(JsonObject request) {
		List<SimpleCategory> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = categoryRepository.findAll();
		}
		
		return result;
	}
}
