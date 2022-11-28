package com.spacex.challenge.task.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.model.ManualTask;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.trello.model.TrelloCard;
import com.spacex.challenge.trello.model.TrelloLabel;
import com.spacex.challenge.trello.model.TrelloList;
import com.spacex.challenge.trello.service.TrelloBoardConsumer;
import com.spacex.challenge.trello.service.TrelloProducer;

@Service
public class ManualTaskWorker implements TaskWorker<ManualTask> {

	@Autowired	
	TrelloBoardConsumer consumer;
	@Autowired
	TrelloProducer producer;
	
	@Override
	public void workTask(ManualTask task) {
		Optional<TrelloList> todoList = consumer.getToDoList("EnpRiV5p");
		TrelloCard tCard = new TrelloCard();
		tCard.setIdBoard("EnpRiV5p");
		tCard.setName(task.getTitle());
		tCard.setIdList(todoList.orElseThrow().getId());
		
		Optional<TrelloLabel> bugLabel = consumer.getLabelFromBoard("EnpRiV5p",task.getCategory());
		String[] labelIds = new String[1];
		labelIds[0] = bugLabel.orElseThrow().getId();
		tCard.setIdLabels(labelIds);
	
		producer.createCard("EnpRiV5p", tCard);
	}

	@Override
	public String getType() {
		return TaskService.TASK_TYPE;
	}

}
