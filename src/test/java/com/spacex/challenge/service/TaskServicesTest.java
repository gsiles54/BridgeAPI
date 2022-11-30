package com.spacex.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacex.challenge.misc.TitleRandomizer;
import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.service.BugTaskService;
import com.spacex.challenge.task.service.IssueTaskService;
import com.spacex.challenge.task.service.ManualTaskService;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.task.worker.WorkerFactory;

@SpringBootTest
public class TaskServicesTest {
	@Autowired
	WorkerFactory taskFactory;
	
	@Autowired
	BugTaskService bugService;
	
	@Autowired
	ManualTaskService manualTaskService;
	
	@Autowired
	IssueTaskService issueTaskService;
	
	
	@Test
	public void testBugService() throws MissingItemAtTrelloBoard {
		TitleRandomizer randomizer = new TitleRandomizer();
		Bug bugTask = new Bug();
		bugTask.setType(TaskService.BUG_TYPE);
		bugTask.setTitle(randomizer.randomizeTitle());
		bugTask.setDescription("dummy desc2");
		
		String response = bugService.handleNewTask(bugTask);
		assertThat(response!=null&&!response.isEmpty());
	}
	@Test
	public void testManualTaskService() throws MissingItemAtTrelloBoard {
		ManualTask manTask = new ManualTask();
		manTask.setType(TaskService.TASK_TYPE);
		manTask.setCategory(TaskService.MAINTENANCE_LABEL);
		manTask.setTitle("Task23");
		
		String response = manualTaskService.handleNewTask(manTask);
		assertThat(response!=null&&!response.isEmpty());
		
	}

	@Test
	public void testIssueTaskService() throws MissingItemAtTrelloBoard {
		Issue issueTask = new Issue();
		issueTask.setType(TaskService.ISSUE_TYPE);
		issueTask.setTitle("IssueTask23");
		issueTask.setDescription("issue");
		
		String response = issueTaskService.handleNewTask(issueTask);
		assertThat(response!=null&&!response.isEmpty());
		
	}
	
	@Test
	public void testWrongIssueTaskType() throws MissingItemAtTrelloBoard {
		Issue issueTask = new Issue();
		issueTask.setType(TaskService.BUG_TYPE);
		issueTask.setTitle("IssueTask23");
		issueTask.setDescription("issue");
		
		String response = issueTaskService.handleNewTask(issueTask);
		assertThat(response!=null&&!response.isEmpty());
		
	}
}
