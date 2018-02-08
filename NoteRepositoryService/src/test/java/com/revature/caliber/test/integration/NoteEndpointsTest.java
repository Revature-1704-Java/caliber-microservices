package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.QCStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteEndpointsTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void createNoteTest() throws Exception {
		Note note = Note.qcBatchNote("Test", 8, new Batch(), QCStatus.Good);
		Gson gson = new Gson();
		mvc.perform(post("http://localhost:8908/note/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(note, Note.class)));
		
	}
}
