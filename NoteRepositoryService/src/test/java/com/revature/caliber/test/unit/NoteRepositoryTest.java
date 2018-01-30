package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.QCStatus;
import com.revature.caliber.repository.NoteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {
	@Autowired
	private NoteRepository noteRepository;
	
	@Test
	public void findOneTest() {
		Note note = noteRepository.findOne(6482);
		short week = 6;
		
		System.out.println(note);
		
		assertNotNull(note);
		assertEquals(NoteType.QC_TRAINEE, note.getType());
		assertEquals(QCStatus.Good, note.getQcStatus());
		assertEquals(week, note.getWeek().shortValue());
	}
	
	@Test
	public void findAllTest() {
		List<Note> notes = noteRepository.findAll();
		
		System.out.println(notes.size());
		
		assertFalse(notes.isEmpty());
	}

}
