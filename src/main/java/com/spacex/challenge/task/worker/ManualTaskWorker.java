package com.spacex.challenge.task.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.configuration.ConfigurationTrello;
import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
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
	@Autowired
	ConfigurationTrello config;
	@Override
	public String workTask(ManualTask task) throws MissingItemAtTrelloBoard{
		Optional<TrelloList> todoList = consumer.getToDoList(config.getBoardId());
		TrelloCard tCard = new TrelloCard();
		
		tCard.setIdBoard(config.getBoardId());
		tCard.setName(task.getTitle());
		tCard.setIdList(todoList.orElseThrow(() -> new MissingItemAtTrelloBoard("To do list doesnt exists")).getId());
		
		Optional<TrelloLabel> label = consumer.getLabelFromBoard(config.getBoardId(),task.getCategory());
		String[] labelIds = new String[1];
		labelIds[0] = label.orElseThrow(() -> new MissingItemAtTrelloBoard(task.getCategory()+" label doesnt exists")).getId();
		tCard.setIdLabels(labelIds);
	
		String responseCardId = producer.createCard(config.getBoardId(), tCard);
		return responseCardId;
	}

	@Override
	public String getType() {
		return TaskService.TASK_TYPE;
	}

}
