package com.revature.caliber.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.repository.TrainerRepository;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerCompositionService {

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	TrainerRepository trainerRepository;

	@Autowired
	private TrainerCompositionMessagingService trainerCompositionMessagingService;

	public Trainer findOne(Integer trainerId) {
		SimpleTrainer basis = trainerRepository.findOne(trainerId);
		Trainer result = composeTrainer(basis);

		System.out.println(result);
		return result;
	}

	private Trainer composeTrainer(SimpleTrainer src) {
		List<SimpleBatch> batchSet = trainerCompositionMessagingService
				.sendSingleSimpleBatchRequest((Integer) src.getTrainerId());

		Trainer dest = new Trainer(src);

		dest.setBatches(batchSet.stream().map(x -> new Batch(x)).collect(Collectors.toSet()));

		return dest;
	}

}