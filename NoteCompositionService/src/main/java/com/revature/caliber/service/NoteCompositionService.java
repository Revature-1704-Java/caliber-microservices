package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.revature.caliber.model.SimpleNote;

public class NoteCompositionService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<SimpleNote> findTraineeNote() {
		
	}
}
