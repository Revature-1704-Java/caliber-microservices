package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Trainee;


@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
	public List<Trainee> findAllByBatchIdWhereTrainingstatusNotEqualDropped(int BID);
}
