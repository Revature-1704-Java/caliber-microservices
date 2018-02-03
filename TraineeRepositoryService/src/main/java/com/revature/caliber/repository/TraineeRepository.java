package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleTrainee;

@Repository
public interface TraineeRepository extends JpaRepository<SimpleTrainee, Integer> {

	List<SimpleTrainee> findAllByEmail(String searchTerm);

	List<SimpleTrainee> findAllByName(String searchTerm);

	List<SimpleTrainee> findAllBySkypeId(String searchTerm);

	List<SimpleTrainee> findAllByBatchId(Integer batchId);
	
}
