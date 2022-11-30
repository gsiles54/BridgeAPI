package com.spacex.challenge.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;

@Service
public class IssueTaskService implements ITaskService<Issue>{
	@Autowired
	WorkerFactory workerFactory;
	


	@Override
	public String handleNewTask(Issue task) throws MissingItemAtTrelloBoard{
		TaskWorker<Issue> worker = workerFactory.getWorker(task.getType());
		return worker.workTask(task);
	}
}
