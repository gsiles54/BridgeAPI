package com.spacex.challenge.task.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.challenge.misc.ITitleRandomizer;

@Service
public class TaskFactory implements ITaskFactory{

	static private final String BUG = "bug";
	static private final String ISSUE = "issue";
	static private final String TASK = "task";
	@Autowired
	private ITitleRandomizer titleRandomizer;
	
	@Override
	public Task createTask(JsonNode task) throws IllegalArgumentException,JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
	
			switch(task.get("type").asText()) {
			case BUG:
				Bug bugTask = obj.treeToValue(task, Bug.class);
				bugTask.setTitle(titleRandomizer.randomizeTitle());
				return bugTask;
			case ISSUE:
				return obj.treeToValue(task, Issue.class);
			case TASK:
				return obj.treeToValue(task, ManualTask.class);
			default:
				throw new IllegalArgumentException("Invalid task type : "+ obj.writeValueAsString(task.findValue("type")));

	}
			}

}
