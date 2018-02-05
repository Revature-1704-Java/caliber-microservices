package com.revature.caliber.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.model.SimpleCategory;
import com.revature.category.message.MessageSender;

/**
 * Provides logic concerning categories data. Application logic has no business
 * being in a DAO nor in a Controller. This is the ideal place for calculations
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class CategoryService {
		
		private static final Logger log = Logger.getLogger(CategoryService.class);
		//private CategoryDAO categoryDAO;
/*
		@Autowired
		public void setCategoryDAO(CategoryDAO categoryDAO) {
			this.categoryDAO = categoryDAO;
		}

		/**
		 * FIND ALL CATEGORIES
		 * 
		 * @return
		 */
	
		@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
		public List<SimpleCategory> findAllActive() {
			log.debug("requesting categories");
			return MessageSender.send("findallactive");
			/*
			log.debug("Requesting categories");
			return categoryDAO.findAllActive();
			*/

		}
	
		/**
		 * FIND ALL CATEGORIES- INCLUDING NOT ACTIVE
		 * 
		 * @return
		 */
	
		public List<SimpleCategory> findAll() {
			
			log.debug("Requesting categories");
			return MessageSender.send("findall");
			
		}
	
		/**
		 * FIND CATEGORY BY ID
		 * 
		 * @param id
		 * @return
		 */
	
		@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
		public SimpleCategory findCategory(int categoryId) {
			
			log.debug("Find category: " + categoryId);
			return MessageSender.send(categoryId);
			
		}
	
		/**
		 * Update CATEGORY
		 * 
		 * @param category
		 */
	
		@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
		public void updateCategory(SimpleCategory category) {
			
			log.debug("Update category: " + category);
			MessageSender.send(category);
			
		}
	
	
		@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
		public void saveCategory(SimpleCategory category) {
			log.debug("Save category: " + category);
			MessageSender.send(category);
		}
		
		
}