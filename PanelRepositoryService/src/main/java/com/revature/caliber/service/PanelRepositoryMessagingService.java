package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.Panel;
import com.revature.caliber.repository.PanelRepository;

@Service
public class PanelRepositoryMessagingService {
	@Autowired
	private PanelRepository panelRepository;
	
	@RabbitListener(queues = "caliber.panel")
    public Panel receive(String message) {
        System.out.println(message);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(message);
        JsonObject request = element.getAsJsonObject();
        
        Gson gson = new Gson();
        if(request.get("methodName").getAsString().equals("findOne")) {
        	Panel panel = panelRepository.findOne(request.get("panelId").getAsInt());
        	return panel;
        } else return null;
    }
}
