package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.revature.caliber.model.SimpleCategory;



@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends JpaRepository<SimpleCategory, Integer> {

	@Query("SELECT DISTINCT c FROM SimpleCategory c ORDER BY c.categoryId ASC")
	List<SimpleCategory> findAll();
	
	@Query("SELECT DISTINCT c FROM SimpleCategory c WHERE c.active=true ORDER BY c.skillCategory ASC")
	List<SimpleCategory> findAllActive();
}
