package com.spacex.challenge.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;

@Service
public class BugTaskService implements ITaskService<Bug>{
	@Autowired
	WorkerFactory workerFactory;



	@Override
	public String handleNewTask(Bug task) throws MissingItemAtTrelloBoard {
		TaskWorker<Bug> worker = workerFactory.getWorker(task.getType());
		return worker.workTask(task);
	}
}
