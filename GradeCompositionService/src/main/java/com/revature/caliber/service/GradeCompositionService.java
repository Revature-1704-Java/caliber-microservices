package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.caliber.model.Grade;

@RestController
@RequestMapping(value = "/grade")
public class GradeCompositionService {
	/*
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/{traineeId}")
	public List<Grade> findByTraineeId(@PathVariable("traineeId") int traineeId) {
		List<Grade> grades = restTemplate.getForObject("http://localhost:8180/grade/search/findByTraineeId?traineeId=" + traineeId, Grade.class);
		
		return grades;
	}
	*/
}
