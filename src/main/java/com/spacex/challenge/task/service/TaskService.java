package com.spacex.challenge.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.challenge.task.model.ITaskFactory;
import com.spacex.challenge.task.model.Task;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;

@Service
public class TaskService{
	
	@Autowired
	ITaskFactory taskFactory;
	@SuppressWarnings("rawtypes")
	@Autowired
	WorkerFactory workerFactory;
	
	public static final String BUG_LABEL = "BUG";
	public static final String MAINTENANCE_LABEL = "MAINTENANCE";
	public static final String RESEARCH_LABEL = "RESEARCH";
	public static final String TEST_LABEL = "TEST";
	
	public static final String BUG_TYPE = "bug";
	public static final String ISSUE_TYPE = "issue";
	public static final String TASK_TYPE = "task";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleNewTask(JsonNode node) throws JsonProcessingException, IllegalArgumentException {
		
		Task aTask = taskFactory.createTask(node);
		
		TaskWorker worker = workerFactory.getWorker(aTask.getType());
		worker.workTask(aTask); 
	}
}
