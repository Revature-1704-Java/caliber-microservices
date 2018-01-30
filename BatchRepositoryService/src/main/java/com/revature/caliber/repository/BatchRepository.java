package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	List <Batch> findAllByTrainerId(Integer trainerId);
}
