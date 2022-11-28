package com.spacex.challenge.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public abstract class TrelloConector {

	
	protected RestTemplate restTemplate;
	
	@Autowired
	public TrelloConector(RestTemplateBuilder restBuilder) {
		restTemplate = restBuilder.build();
	}
	
	protected final String KEY = "bc327ce407eeec61c8a9e4f6899e9f80";
	protected final String TOKEN = "bebf99f012b3d7aa102e0a502dc511fc3fba6af98b6ce7717fa876b96eac6c65";
	protected final String BASE_URL = "https://api.trello.com/1";//;EnpRiV5p?key=bc327ce407eeec61c8a9e4f6899e9f80&token=bebf99f012b3d7aa102e0a502dc511fc3fba6af98b6ce7717fa876b96eac6c65";
	protected final String BOARD_URI = "/boards/{id}";
	protected final String KEYTOKEN= "?key={key}&token={token}";
	
}
