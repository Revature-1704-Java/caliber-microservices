package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.Batch;
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	List <Batch> findAllByTrainerId(Integer trainerId);
}
