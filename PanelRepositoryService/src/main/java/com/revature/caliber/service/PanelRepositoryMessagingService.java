package com.revature.caliber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.repository.PanelRepository;

@Service
public class PanelRepositoryMessagingService {

	@Autowired
	private PanelRepositoryRequestDispatcher panelRepositoryRequestDispatcer;

	@RabbitListener(queues = "revature.caliber.repos.panel")
	public SimplePanel receiveSingleSimplePanelRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return panelRepositoryRequestDispatcer.processSingleSimplePanelRequest(request);

	}
}
