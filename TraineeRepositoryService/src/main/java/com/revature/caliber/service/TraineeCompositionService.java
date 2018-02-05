package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.TraineeRepository;

@Service
public class TraineeCompositionService {
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private TraineeCompositionMessagingService traineeCompositionMessagingService;

	public void save(Trainee trainee) {
		SimpleTrainee simpleTrainee = new SimpleTrainee(trainee);
		traineeRepository.save(simpleTrainee);
	}

	public List<Trainee> findAll() {
		List<SimpleTrainee> basis = traineeRepository.findAll();
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	public List<Trainee> findAllNotDropped() {
		List<SimpleTrainee> basis = traineeRepository.findAllByTrainingStatusNot(TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);
		
		return trainees;
	}

	public List<Trainee> findAllByBatch(Integer batchId) {
		List<SimpleTrainee> basis = traineeRepository.findAllByBatchIdAndTrainingStatusNot(batchId, TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	public List<Trainee> findAllDroppedByBatch(Integer batchId) {
		List<SimpleTrainee> basis = traineeRepository.findAllByBatchIdAndTrainingStatus(batchId, TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);
		
		return trainees;
	}
	
	public List<Trainee> findAllByTrainer(Integer trainerId) {
		List<SimpleBatch> trainerBatches = traineeCompositionMessagingService.sendListSimpleBatchRequest(trainerId);
		List<SimpleTrainee> basis = new LinkedList<SimpleTrainee>();//traineeRepository.findAllByBatchIdAndTrainingStatusNot(trainerId, TrainingStatus.Dropped);
		List<Trainee> trainees = null;
		
		for(SimpleBatch b : trainerBatches) {
			List<SimpleTrainee> batchTrainees = traineeRepository.findAllByBatchIdAndTrainingStatusNot(b.getBatchId(), TrainingStatus.Dropped);
			basis.addAll(batchTrainees);
		}
		
		trainees = composeListOfTrainees(basis);
		
		return trainees;
	}
	
	public Trainee findOne(Integer traineeId) {
		SimpleTrainee basis = traineeRepository.findOneByTraineeIdAndTrainingStatusNot(traineeId, TrainingStatus.Dropped);
		Trainee result = composeTrainee(basis);

		return result;
	}
	
	public List<Trainee> findByEmail(String email) {
		List<SimpleTrainee> basis = traineeRepository.findAllByEmailLikeAndTrainingStatusNot(email, TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);
		
		return trainees;
	}
	
	public List<Trainee> findByName(String name) {
		List<SimpleTrainee> basis = traineeRepository.findAllByNameLikeAndTrainingStatusNot(name, TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);
		
		return trainees;
	}
	
	public List<Trainee> findBySkypeId(String skypeId) {
		List<SimpleTrainee> basis = traineeRepository.findAllBySkypeIdLikeAndTrainingStatusNot(skypeId, TrainingStatus.Dropped);
		List<Trainee> trainees = composeListOfTrainees(basis);
		
		return trainees;
	}

	public void delete(Trainee trainee) {
		traineeRepository.delete(trainee.getTraineeId());
	}

	public void update(Trainee trainee) {
		save(trainee);
	}

	private List<Trainee> composeListOfTrainees(List<SimpleTrainee> simpleTrainees) {
		List<Trainee> trainees = new LinkedList<Trainee>();

		for (SimpleTrainee simpleTrainee : simpleTrainees) {
			Trainee trainee = composeTrainee(simpleTrainee);
			trainees.add(trainee);
		}

		return trainees;
	}

	private Trainee composeTrainee(SimpleTrainee simpleTrainee) {
		Trainee trainee = new Trainee(simpleTrainee);

		SimpleBatch simpleBatch = traineeCompositionMessagingService
				.sendSingleSimpleBatchRequest(simpleTrainee.getBatchId());
		Batch batch = new Batch(simpleBatch);
		trainee.setBatch(batch);

		return trainee;
	}
}