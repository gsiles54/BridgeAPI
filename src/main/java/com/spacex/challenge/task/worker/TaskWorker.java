package com.spacex.challenge.task.worker;

import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;

public interface TaskWorker<T> {

	
	public String workTask(T aTask) throws MissingItemAtTrelloBoard;
	
	public String getType();
}
