package com.revature.caliber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	Batch findAllByTrainerId(Integer trainerId);
}
