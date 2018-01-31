package com.revature.caliber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.SimpleNote;

@RestController
@RequestMapping(value = "/note")
public class NoteCompositionService {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/{traineeId}/{week}")
	public SimpleNote findTraineeNote(@PathVariable("traineeId") int traineeId, @PathVariable("week") short week) {
		SimpleNote simpleTraineeNote = restTemplate.getForObject("http://localhost:8180/note/search/findByTraineeIdAndWeekAndQcFeedbackAndType?traineeId=" + traineeId + "&week=" + week + "&qcFeedback=" + false + "&type=" + NoteType.TRAINEE, SimpleNote.class);
		
		return simpleTraineeNote;
	}
}
