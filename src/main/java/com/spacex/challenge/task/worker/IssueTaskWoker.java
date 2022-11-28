package com.spacex.challenge.task.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.model.Issue;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.trello.model.TrelloCard;
import com.spacex.challenge.trello.model.TrelloList;
import com.spacex.challenge.trello.service.TrelloBoardConsumer;
import com.spacex.challenge.trello.service.TrelloProducer;
@Service
public class IssueTaskWoker implements TaskWorker<Issue> {

	@Autowired	
	TrelloBoardConsumer consumer;
	@Autowired
	TrelloProducer producer;
	
	@Override
	public void workTask(Issue task) {
		Optional<TrelloList> todoList = consumer.getToDoList("EnpRiV5p");
		TrelloCard tCard = new TrelloCard();
		tCard.setIdBoard("EnpRiV5p");
		tCard.setDesc(task.getDescription());
		tCard.setName(task.getTitle());
		tCard.setIdList(todoList.orElseThrow().getId());
		
		producer.createCard("EnpRiV5p", tCard);
	}

	@Override
	public String getType() {
		return TaskService.ISSUE_TYPE;
	}

}
