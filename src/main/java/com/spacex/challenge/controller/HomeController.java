package com.spacex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.service.BugTaskService;
import com.spacex.challenge.task.service.IssueTaskService;
import com.spacex.challenge.task.service.ManualTaskService;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.trello.service.TrelloBoardConsumer;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	TaskService generaltaskService;
	@Autowired
	BugTaskService bugTaskService;
	
	@Autowired
	ManualTaskService manualTaskService;
	
	@Autowired
	IssueTaskService issueTaskService;
	
	@Autowired
	TrelloBoardConsumer cons;

	@PostMapping(value="/",consumes = "application/json")
	public ResponseEntity<String> createTask(@RequestBody JsonNode jsonTask) throws IllegalArgumentException,JsonProcessingException, MissingItemAtTrelloBoard{
		
	 String cardIdResponse = generaltaskService.handleNewTask(jsonTask);
	 ResponseEntity<String> response = new ResponseEntity<>(cardIdResponse,HttpStatus.OK);
	 
		return response;
	}
	
	@PostMapping(value="/bug",consumes = "application/json")
	public ResponseEntity<String> createBugTask(@RequestBody Bug bugTask) throws IllegalArgumentException,JsonProcessingException, MissingItemAtTrelloBoard{
		
		 String cardIdResponse = bugTaskService.handleNewTask(bugTask);
		 ResponseEntity<String> response = new ResponseEntity<>(cardIdResponse,HttpStatus.OK);
		 
			return response;
	}
	
	@PostMapping(value="/task",consumes = "application/json")
	public ResponseEntity<String> createManualTask(@RequestBody ManualTask manualTask) throws IllegalArgumentException,JsonProcessingException, MissingItemAtTrelloBoard{
		
		 String cardIdResponse = manualTaskService.handleNewTask(manualTask);
		 ResponseEntity<String> response = new ResponseEntity<>(cardIdResponse,HttpStatus.OK);
		 
			return response;
	}
	
	@PostMapping(value="/issue",consumes = "application/json")
	public ResponseEntity<String> createTask(@RequestBody Issue issueTask) throws IllegalArgumentException,JsonProcessingException, MissingItemAtTrelloBoard{
		
		 String cardIdResponse = issueTaskService.handleNewTask(issueTask);
		 ResponseEntity<String> response = new ResponseEntity<>(cardIdResponse,HttpStatus.OK);
		 
			return response;
	}
}
