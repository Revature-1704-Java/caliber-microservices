package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.SimpleBatch;
//import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.repository.TrainerRepository;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerCompositionService {

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	TrainerRepository trainerRepository;

	public List<Batch> send(String routingKey, String message) {
		JsonObject object = new JsonObject();

		object.addProperty("methodName", "findAllByTrainerId");
		object.addProperty("trainerId", 17);

		List<Batch> response = (List<Batch>) rabbitTemplate.convertSendAndReceive("revature.caliber.repos", routingKey,
				object.toString());
		System.out.println(response);

		return response;
	}

}
