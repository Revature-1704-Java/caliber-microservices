package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
/**
 * Message Listener
 * The queue in which the message comes in determines the type of composition.
 *  
 * @author Samuel Huang
 */
@Service
public class TraineeRepositoryMessagingService {

	@Autowired
	private TraineeRepositoryRequestDispatcher traineeRepositoryRequestDispatcher;

	/**
	 * sphuang 02/08/2018 
	 * Parses message in queue to a string json object.
	 * RequestDispatcher then processes the message and returns a SimpleTrainee.
	 * 
	 * 
	 * @param String - message
	 * @return SimpleTrainee 
	 * Parse a String for SimpleTrainee
	 *
	 * @param Message
	 *
	 * @return SimpleTrainee
	 */
	@RabbitListener(queues = "revature.caliber.repos.trainee")
	public SimpleTrainee receiveSingleSimpleTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processSingleSimpleTraineeRequest(request);
	}

	/**
	 * sphuang 02/08/2018 
	 * Parses message in queue to a string json object.
	 * RequestDispatcher then processes the message and returns a List of SimpleTrainee.
	 * 
	 * 
	 * @param String - message
	 * @return List<SimpleTrainee> - List of simple Trainees

	 * Parse a String for List of SimpleTrainee
	 *
	 * @param Message
	 *
	 * @return List of SimpleTrainee
	 */
	@RabbitListener(queues = "revature.caliber.repos.trainee.list")
	public List<SimpleTrainee> receiveListSimpleTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processListSimpleTraineeRequest(request);
	}
	
	/**
	 * sphuang 02/08/2018 
	 * Parses message in queue to a string json object.
	 * RequestDispatcher then processes the message and returns a List of ComplexTrainees.
	 * 
	 * 
	 * @param String - message
	 * @return List<SimpleTrainee> - List of simple Trainees
	 * Parse a String for List of Trainee
	 *
	 * @param Message
	 *
	 * @return List of Trainee
	 */
	@RabbitListener(queues = "revature.caliber.service.trainee.list")
	public List<Trainee> receiveListTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processListTraineeRequest(request);
	}
}
