package com.spacex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.challenge.service.TaskService;
import com.spacex.challenge.taskmodel.Task;

@RestController
@RequestMapping("/ASD")
public class HomeController {
	@Autowired
	TaskService taskService;
	/*@PostMapping(value="/q",consumes = "application/json")
	public ResponseEntity<Task> createTask(@RequestBody Bug bugtask){
		System.out.println("Bug");
		return null;
	}
	@PostMapping(value="/",consumes = "application/json")
	public ResponseEntity<Task> createTask(@RequestBody ManualTask manualTask){
		System.out.println("Manual");
		return null;
	}
	@PostMapping(value="/",consumes = "application/json")
	public ResponseEntity<Task> createTask(@RequestBody Issue issue){
		System.out.println("Issue");
		return null;
	}*/
	@PostMapping(value="/",consumes = "application/json")
	public ResponseEntity<Task> createTask(@RequestBody JsonNode bugtask) throws IllegalArgumentException,JsonProcessingException{
		
		Task currentTask = taskService.handleNewTask(bugtask);
		System.out.println(currentTask.getTitle());
		return null;
	}
}
