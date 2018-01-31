package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT DISTINCT c FROM Category c ORDER BY c.categoryId ASC")
	List<Category> findAll();
	
	@Query("SELECT DISTINCT c FROM Category c WHERE c.active=true ORDER BY c.skillCategory ASC")
	List<Category> findAllActive();
}
