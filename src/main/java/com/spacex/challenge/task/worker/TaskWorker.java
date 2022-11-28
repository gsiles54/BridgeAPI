package com.spacex.challenge.task.worker;

import com.spacex.challenge.task.model.Task;

public interface TaskWorker<T extends Task> {

	
	public void workTask(T aTask);
	
	public String getType();
}
