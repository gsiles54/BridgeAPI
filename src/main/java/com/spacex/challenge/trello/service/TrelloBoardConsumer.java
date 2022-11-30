package com.spacex.challenge.trello.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spacex.challenge.trello.model.TrelloMember;

import jakarta.annotation.PostConstruct;

import com.spacex.challenge.configuration.ConfigurationTrello;
import com.spacex.challenge.trello.model.TrelloLabel;
import com.spacex.challenge.trello.model.TrelloList;

@Service
public class TrelloBoardConsumer extends TrelloConector{
	
	

	public TrelloBoardConsumer(RestTemplateBuilder restBuilder) {
		super(restBuilder);
	}

	private final String MEMBERS_URI = "/members";
	private final String LABELS_URI = "/labels";
	private final String LISTS_URI= "/lists";
	private final String TO_DO_LIST = "To Do";
	
	private String idBoard;
	@Autowired
	ConfigurationTrello config;
	
	@PostConstruct
	private void loadIdBoard() {
		idBoard=config.getBoardId();
	}
	public List<TrelloList> getListsFromBoard(String idBoard) {
			
		    Map<String, Object> map = buildDefaultVariableMap(idBoard);
		   
		    String url = buildDefaulListBoardURI();
		 
		    ResponseEntity<TrelloList[]> responseEntity = this.restTemplate.getForEntity(url, TrelloList[].class, map);
		    return Arrays.stream(responseEntity.getBody())
		    .collect(Collectors.toList());
	}

	
	public List<TrelloMember> getListsOfMembersFromBoard(String idBoard) {
		 ResponseEntity<TrelloMember[]> entityResponse = getMembersOfABoard(idBoard);
	    
	 return   Arrays.stream(entityResponse.getBody())
			 		.collect(Collectors.toList());
	}
	
	
	public Optional<TrelloMember> getRandomMemberFromBoard(String idBoard) {
	  

	    List<TrelloMember> memberList=  getListsOfMembersFromBoard(idBoard);
	    Collections.shuffle(memberList);
	    
	    return memberList.isEmpty()?Optional.empty():Optional.of(memberList.get(0));
	  
	    
	}


	public  ResponseEntity<TrelloMember[]> getMembersOfABoard(String idBoard) {
		Map<String, Object> map = buildDefaultVariableMap(idBoard);
	   
	  String url =buildDefaultMembersBoardURI();
	  ResponseEntity<TrelloMember[]> responseEntity =
	    		   restTemplate.getForEntity(url, TrelloMember[].class,map);
	    return responseEntity;
	    	    
	}
	

	public Optional<TrelloLabel> getLabelFromBoard(String idBoard, String labelName) {
	    Map<String, Object> map = buildDefaultVariableMap(idBoard);
	   
	  String url =buildDefaultLabelBoardURI();
	    ResponseEntity<TrelloLabel[]> responseEntity =
	    		   restTemplate.getForEntity(url, TrelloLabel[].class,map);
	    
	   return Arrays.stream(responseEntity.getBody()).filter(x -> x.getName().equals(labelName)).findAny();
		 
    
	}
	
	
	public Optional<TrelloList> getToDoList(String idBoard) {
	    Map<String, Object> map = buildDefaultVariableMap(idBoard);
	   
	  String url =buildDefaulListBoardURI();
	    ResponseEntity<TrelloList[]> responseEntity =
	    		   restTemplate.getForEntity(url, TrelloList[].class,map);
	    
	   return Arrays.stream(responseEntity.getBody()).filter(x -> x.getName().equals(TO_DO_LIST)).findAny();
		 
    
	}
	
	private Map<String, Object> buildDefaultVariableMap(String idBoard) {
		Map<String, Object> map = new HashMap<>();
	    map.put("id", idBoard);
	    map.put("key", KEY);
	    map.put("token",TOKEN);
		return map;
	}
	
	private String buildDefaultMembersBoardURI() {
		return BASE_URL+BOARD_URI+MEMBERS_URI+KEYTOKEN;
	}
	
	private String buildDefaulListBoardURI() {
		return BASE_URL+BOARD_URI+LISTS_URI+KEYTOKEN;
	}
	
	private String buildDefaultLabelBoardURI() {
		return BASE_URL+BOARD_URI+LABELS_URI+KEYTOKEN;
	}
}
