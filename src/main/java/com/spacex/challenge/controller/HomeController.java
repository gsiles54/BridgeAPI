package com.spacex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.challenge.task.model.Task;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.trello.service.TrelloBoardConsumer;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	TaskService taskService;
	@Autowired
	TrelloBoardConsumer cons;

	@PostMapping(value="/",consumes = "application/json")
	public ResponseEntity<Task> createTask(@RequestBody JsonNode jsonTask) throws IllegalArgumentException,JsonProcessingException{
		
	 taskService.handleNewTask(jsonTask);
		return null;
	}
}
