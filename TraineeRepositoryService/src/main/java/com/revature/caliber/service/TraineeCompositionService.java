package com.revature.caliber.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.repository.TraineeRepository;

public class TraineeCompositionService {
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private TraineeCompositionMessagingService traineeCompositionMessagingService;

	public void save(Trainee trainee) {

	}

	public List<Trainee> findAllTrainees() {
		List<SimpleTrainee> basis = traineeRepository.findAll();
		List<Trainee> trainees = (List<Trainee>) composeListOfTrainees(basis);

		return trainees;
	}

	public List<Trainee> findAllTraineesByBatch(Integer batchId) {
		List<SimpleTrainee> basis = traineeRepository.findAllByBatchId(batchId);
		List<Trainee> trainees = (List<Trainee>) composeListOfTrainees(basis);

		return trainees;
	}

//	public List<Trainee> findAllDroppedTraineesByBatch(Integer batchId) {
//		List<SimpleTrainee> basis = traineeRepository.findAll();
//		List<Trainee> trainees = (List<Trainee>) composeListOfTrainees(basis);
//
//		return trainees;
//	}

//	public List<Trainee> findAllTraineesByTrainer(int trainerId) {
//		List<SimpleTrainee> basis = traineeRepository.findAll();
//		List<Trainee> trainees = (List<Trainee>) composeListOfTrainees(basis);
//
//		return trainees;
//	}

	public Trainee findTrainee(Integer traineeId) {
		SimpleTrainee basis = traineeRepository.findOne(traineeId);
		Trainee result = composeTrainee(basis);

		return result;
	}

	public Set<Trainee> search(String searchTerm) {
		Set<SimpleTrainee> result = new HashSet<>();
		List<SimpleTrainee> traineeByEmail = traineeRepository.findAllByEmail(searchTerm);
		result.addAll(traineeByEmail);
		List<SimpleTrainee> traineeByName = traineeRepository.findAllByName(searchTerm);
		result.addAll(traineeByName);
		List<SimpleTrainee> traineeBySkypeId = traineeRepository.findAllBySkypeId(searchTerm);
		result.addAll(traineeBySkypeId);

		Set<Trainee> traineeSet = (Set<Trainee>) composeListOfTrainees(result);
		
		return traineeSet;
	}

	public void delete(Trainee trainee) {
	}

	public void update(Trainee trainee) {
	}

	private Collection<Trainee> composeListOfTrainees(Collection<SimpleTrainee> simpleTrainees) {
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