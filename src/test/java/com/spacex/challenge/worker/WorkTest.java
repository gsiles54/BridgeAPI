package com.spacex.challenge.worker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;

@SpringBootTest
public class WorkTest {
	@Autowired
	WorkerFactory taskFactory;
	
	@Test
	public void testBugWorkerCreation() {
		TaskWorker<Bug> bugWorker =taskFactory.getWorker(TaskService.BUG_TYPE);
		assertThat(bugWorker.getType().equals(TaskService.BUG_TYPE));
	}
	@Test
	public void testTaskWorkerCreation() {
		TaskWorker<ManualTask> taskWorker =taskFactory.getWorker(TaskService.TASK_TYPE);
		assertThat(taskWorker.getType().equals(TaskService.TASK_TYPE));
	}
	
	@Test
	public void testIssueWorkerCreation() {
		TaskWorker<Issue> issueWorker =taskFactory.getWorker(TaskService.ISSUE_TYPE);
		assertThat(issueWorker.getType().equals(TaskService.ISSUE_TYPE));
	}
	@Test
	public void taskWithWrongType() {
		TaskWorker<Issue> issueWorker =taskFactory.getWorker(TaskService.BUG_TYPE);
		assertThat(!issueWorker.getType().equals(TaskService.ISSUE_TYPE));
	}
}
