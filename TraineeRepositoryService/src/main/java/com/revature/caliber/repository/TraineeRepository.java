package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.TrainingStatus;

@Repository
public interface TraineeRepository extends JpaRepository<SimpleTrainee, Integer> {
	SimpleTrainee findOneByTraineeIdAndTrainingStatusNot(Integer traineeId, TrainingStatus status);
	
	List<SimpleTrainee> findAllByEmailLikeAndTrainingStatusNot(String email, TrainingStatus status);

	List<SimpleTrainee> findAllByNameLikeAndTrainingStatusNot(String name, TrainingStatus status);

	List<SimpleTrainee> findAllBySkypeIdLikeAndTrainingStatusNot(String skypeId, TrainingStatus status);

	List<SimpleTrainee> findAllByBatchIdAndTrainingStatusNot(Integer batchId, TrainingStatus status);
	
	List<SimpleTrainee> findAllByBatchIdAndTrainingStatus(Integer batchId, TrainingStatus status);
	
	List<SimpleTrainee> findAllByTrainingStatusNot(TrainingStatus status);
	
	List<SimpleTrainee> findAllByBatchId(Integer batchId);
	
}
