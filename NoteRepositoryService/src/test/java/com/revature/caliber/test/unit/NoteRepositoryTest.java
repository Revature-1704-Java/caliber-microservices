package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.QCStatus;
import com.revature.caliber.repository.NoteRepository;

public class NoteRepositoryTest {
	@Autowired
	private NoteRepository noteRepository;
	
	@Test
	public void findOneTest() {
//		Note note = noteRepository.findOne(6482);
//		short week = 6;
//		
//		assertEquals("Good", note.getContent());
//		assertEquals(NoteType.QC_TRAINEE, note.getType());
//		assertEquals(QCStatus.Good, note.getQcStatus());
//		assertEquals(week, note.getWeek().shortValue());
	}

}
