package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteCompositionMessagingService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_NOTE_ROUTING_KEY = "JBPpcrjZn59EGH4k";
	private static final String LIST_NOTE_ROUTING_KEY = "cf22J9CGs8V95Rbm";
	
	public boolean sendSingleNoteRequest() {
		
		return true;
	}
	
	public boolean sendListNoteRequest() {
		
		return true;
	}
}
