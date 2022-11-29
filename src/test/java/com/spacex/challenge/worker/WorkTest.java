package com.spacex.challenge.worker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacex.challenge.misc.ITitleRandomizer;
import com.spacex.challenge.misc.TitleRandomizer;
import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.model.ITaskFactory;
import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.model.Task;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.task.worker.TaskWorker;
import com.spacex.challenge.task.worker.WorkerFactory;
@SpringBootTest
public class WorkTest {
	
	@Test
	@Autowired
	public void testBugWorkerCreation(WorkerFactory<Bug> taskFactory) {
		TaskWorker<Bug> bugWorker =taskFactory.getWorker(TaskService.BUG_TYPE);
		assertThat(bugWorker.getType().equals(TaskService.BUG_TYPE));
	}
	@Test
	@Autowired
	public void testTaskWorkerCreation(WorkerFactory<ManualTask> taskFactory) {
		TaskWorker<ManualTask> taskWorker =taskFactory.getWorker(TaskService.TASK_TYPE);
		assertThat(taskWorker.getType().equals(TaskService.TASK_TYPE));
	}
	
	@Test
	@Autowired
	public void testIssueWorkerCreation(WorkerFactory<Issue> taskFactory) {
		TaskWorker<Issue> issueWorker =taskFactory.getWorker(TaskService.ISSUE_TYPE);
		assertThat(issueWorker.getType().equals(TaskService.ISSUE_TYPE));
	}
}
