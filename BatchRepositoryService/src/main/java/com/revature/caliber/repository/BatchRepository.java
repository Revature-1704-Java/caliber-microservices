package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.Batch;
@RepositoryRestResource(collectionResourceRel = "batch", path = "batch")
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	List <Batch> findAllByTrainerId(@Param("trainerId") Integer trainerId);
}
