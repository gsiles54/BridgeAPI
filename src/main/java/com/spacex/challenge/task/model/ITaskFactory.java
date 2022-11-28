package com.spacex.challenge.task.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface ITaskFactory {

	public Task createTask(JsonNode task) throws JsonProcessingException, IllegalArgumentException;
}
