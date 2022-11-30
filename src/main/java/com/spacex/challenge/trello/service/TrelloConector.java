package com.spacex.challenge.trello.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spacex.challenge.configuration.ConfigurationTrello;

import jakarta.annotation.PostConstruct;
@Service
public abstract class TrelloConector {

	
	protected RestTemplate restTemplate;
	
	@Autowired
	protected ConfigurationTrello config;
	
	@Autowired
	public TrelloConector(RestTemplateBuilder restBuilder) {
		restTemplate = restBuilder.build();
	}
	
	protected  String KEY;
	protected  String TOKEN;
	protected final String BASE_URL = "https://api.trello.com/1";//;EnpRiV5p?key=bc327ce407eeec61c8a9e4f6899e9f80&token=bebf99f012b3d7aa102e0a502dc511fc3fba6af98b6ce7717fa876b96eac6c65";
	protected final String BOARD_URI = "/boards/{id}";
	protected final String KEYTOKEN= "?key={key}&token={token}";
	
	@PostConstruct
	protected void init() {
		KEY = config.getPublicKey();
		TOKEN = config.getToken();
	}
	
	protected Map<String, Object> buildDefaultVariableMap(String idBoard) {
		Map<String, Object> map = new HashMap<>();
	    map.put("id", idBoard);
	    map.put("key", config.getPublicKey());
	    map.put("token", config.getToken());
		return map;
	}
}
