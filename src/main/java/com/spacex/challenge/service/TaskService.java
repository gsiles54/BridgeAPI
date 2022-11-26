package com.spacex.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.challenge.taskmodel.ITaskFactory;
import com.spacex.challenge.taskmodel.Task;

@Service
public class TaskService {
	
	@Autowired
	ITaskFactory factory;
	
	public Task handleNewTask(JsonNode node) throws JsonProcessingException, IllegalArgumentException {
		
		Task aTask = factory.createTask(node);
		System.out.println(aTask);
		return aTask;
	}
}
