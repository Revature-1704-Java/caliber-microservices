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
	private static final int TEST_TRAINEE_ID = 5529;
	private static final int TEST_BATCH_ID = 2100;
	private static final int TEST_QC_TRAINEE_ID = 5532;
	private static final int TEST_QC_BATCH_ID = 2201;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Test
	public void findOneTest() {
		Note note = noteRepository.findOne(5061);
		short week = 2;
		
		System.out.println(note);
		
		assertNotNull(note);
		assertEquals("Associate cannot keep up with the pace of coding his project and learning in class. No confidence during the interview and did not answer most SQL questions correctly. Associate will be dropped.", note.getContent());
		assertEquals(NoteType.TRAINEE, note.getType());
		assertEquals(week, note.getWeek().shortValue());
	}
	
	@Test
	public void findAllTest() {
		List<Note> notes = noteRepository.findAll();
		
		System.out.println(notes.size());
		
		assertFalse(notes.isEmpty());
	}
	
	@Test
	public void findByBatchIdAndWeekAndQcFeedbackAndTypeTest() {
		short week = 2;
		List<Note> notes = noteRepository.findByBatchIdAndWeekAndQcFeedbackAndType(TEST_BATCH_ID, week, false, NoteType.BATCH);
		Note testNote = notes.get(0);
		
		System.out.println(notes);
		
		assertFalse(notes.isEmpty());
		assertEquals(TEST_BATCH_ID, testNote.getBatchId().intValue());
		assertEquals(week, testNote.getWeek().shortValue());
		assertEquals(false, testNote.isQcFeedback());
		assertEquals(NoteType.BATCH, testNote.getType());
	}
	
	@Test
	public void findByTraineeIdAndTypeTest() {
		short week = 1;
		List<Note> notes = noteRepository.findByTraineeIdAndType(TEST_TRAINEE_ID, NoteType.TRAINEE);
		Note testNote = notes.get(0);
		
		System.out.println("Find By TraineeId and Type\n" + notes);
		
		assertEquals(week, testNote.getWeek().shortValue());
		assertEquals("Technically okay. Very nervous in interview. Conducts self professionally", testNote.getContent());
		assertEquals(TEST_TRAINEE_ID, testNote.getTraineeId().intValue());
		assertEquals(2201, testNote.getBatchId().intValue());
		assertEquals(NoteType.TRAINEE, testNote.getType());
	}
	
	@Test
	public void findByTraineeIdAndWeekAndQcFeedbackAndTypeTest() {
		short week = 2;
		Note testNote = noteRepository.findByTraineeIdAndWeekAndQcFeedbackAndType(TEST_TRAINEE_ID, week, false, NoteType.TRAINEE);
		
		System.out.println("Find By TraineeId and Week and qcFeedback and Type:\n" + testNote);
		
		assertEquals(week, testNote.getWeek().shortValue());
		assertEquals("Good communication. Not as confident and less use of technical terms", testNote.getContent());
		assertEquals(TEST_TRAINEE_ID, testNote.getTraineeId().intValue());
		assertEquals(false, testNote.isQcFeedback());
		assertEquals(NoteType.TRAINEE, testNote.getType());
	}

}
