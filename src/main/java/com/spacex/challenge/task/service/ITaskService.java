package com.spacex.challenge.task.service;

import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;

public interface ITaskService<T>{

	
	public String handleNewTask(T task) throws MissingItemAtTrelloBoard;
	
}
