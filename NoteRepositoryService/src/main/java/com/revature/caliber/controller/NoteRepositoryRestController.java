package com.revature.caliber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.repository.NoteRepository;

@RestController
@RequestMapping(value = "/note")
public class NoteRepositoryRestController {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<SimpleNote>> getAllNotes() {
		List<SimpleNote> notes = noteRepository.findAll();
		return new ResponseEntity<List<SimpleNote>>(notes, HttpStatus.OK);
	}
}
