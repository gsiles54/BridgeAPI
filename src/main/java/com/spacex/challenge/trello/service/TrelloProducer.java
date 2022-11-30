package com.spacex.challenge.trello.service;

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


		
		public String createCard(String idBoard,TrelloCard tCard) {
			
			   Map<String, Object> map = buildDefaultVariableMap(idBoard);
			    
			    HttpEntity<TrelloCard> request = new HttpEntity<>(tCard);
			   restTemplate.postForObject(BASE_URL +cardURI + KEYTOKEN, request, TrelloCard.class, map);
			   
			   return request.getBody().getName();
		}
		
	
		
}
