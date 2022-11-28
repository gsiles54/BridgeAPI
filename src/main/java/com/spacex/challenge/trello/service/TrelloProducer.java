package com.spacex.challenge.trello.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.spacex.challenge.trello.model.TrelloCard;

@Service
public class TrelloProducer extends TrelloConector{
	
	private final String cardURI = "/cards";
	

	public TrelloProducer(RestTemplateBuilder restBuilder) {
		super(restBuilder);
	}


		
		public void createCard(String idBoard,TrelloCard tCard) {
			   Map<String, Object> map = new HashMap<>();
			    map.put("id", idBoard);
			    map.put("key", KEY);
			    map.put("token",TOKEN);
			    
			    HttpEntity<TrelloCard> request = new HttpEntity<>(tCard);
			   restTemplate.postForObject(BASE_URL +cardURI + KEYTOKEN, request, TrelloCard.class, map);
			    
		}
		
	
		
}
