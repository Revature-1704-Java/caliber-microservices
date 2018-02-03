package com.revature.caliber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.repository.PanelRepository;

@Service
public class PanelRepositoryRequestDispatcher {
	
	@Autowired
	private PanelRepository panelRepository;
	
	public SimplePanel processSingleSimplePanelRequest(JsonObject request) {
		SimplePanel result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Integer panelId = request.get("panelId").getAsInt();
			result = panelRepository.findOne(panelId);
		}
		return result;
	}
}
