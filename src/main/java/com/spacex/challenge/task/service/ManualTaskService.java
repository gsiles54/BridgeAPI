package com.spacex.challenge.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;

@Service
public class ManualTaskService implements ITaskService<ManualTask>{
	@Autowired
	WorkerFactory workerFactory;
	
	



	@Override
	public String handleNewTask(ManualTask task) throws MissingItemAtTrelloBoard{
		TaskWorker<ManualTask> worker = workerFactory.getWorker(task.getType());
		return worker.workTask(task);
	}
}
