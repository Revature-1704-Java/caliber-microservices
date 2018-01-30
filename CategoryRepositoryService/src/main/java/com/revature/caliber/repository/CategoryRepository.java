package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleCategory;

@Repository
public interface CategoryRepository extends JpaRepository<SimpleCategory, Integer> {

	@Query("SELECT DISTINCT c FROM Category c ORDER BY c.categoryId ASC")
	List<SimpleCategory> findAll();
	
	@Query("SELECT DISTINCT c FROM Category c WHERE c.active=true ORDER BY c.skillCategory ASC")
	List<SimpleCategory> findAllActive();
}
