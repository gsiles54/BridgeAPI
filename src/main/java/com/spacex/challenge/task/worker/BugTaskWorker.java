package com.spacex.challenge.task.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.configuration.ConfigurationTrello;
import com.spacex.challenge.misc.ITitleRandomizer;
import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;
import com.spacex.challenge.task.model.Bug;
import com.spacex.challenge.task.service.TaskService;
import com.spacex.challenge.trello.model.TrelloMember;
import com.spacex.challenge.trello.model.TrelloCard;
import com.spacex.challenge.trello.model.TrelloLabel;
import com.spacex.challenge.trello.model.TrelloList;
import com.spacex.challenge.trello.service.TrelloBoardConsumer;
import com.spacex.challenge.trello.service.TrelloProducer;

@Service
public class BugTaskWorker implements TaskWorker<Bug> {
	@Autowired	
	TrelloBoardConsumer consumer;
	@Autowired
	TrelloProducer producer;

	@Autowired
	ConfigurationTrello config;
	
	@Autowired
	ITitleRandomizer titleRandomizer;
	@Override
	public String workTask(Bug task) throws MissingItemAtTrelloBoard{
		if(task.getTitle().isEmpty()){
			task.setTitle(titleRandomizer.randomizeTitle());
		}
		Optional<TrelloMember> randomMember = consumer.getRandomMemberFromBoard(config.getBoardId());
		String cardId = randomMember.orElseThrow(() -> new MissingItemAtTrelloBoard("There are no members on the board")).getId();
		String[] cardIds = new String[1];
		cardIds[0] = cardId;
		
		TrelloCard tCard = new TrelloCard();
		tCard.setIdBoard(config.getBoardId());
		tCard.setDesc(task.getDescription());
		tCard.setIdMembers(cardIds);
		tCard.setName(task.getTitle());

		Optional<TrelloLabel> bugLabel = consumer.getLabelFromBoard(config.getBoardId(),TaskService.BUG_LABEL);
		String[] labelIds = new String[1];
		labelIds[0] = bugLabel.orElseThrow(() -> new MissingItemAtTrelloBoard("Bug label doesnt exists")).getId();
		tCard.setIdLabels(labelIds);
		
		Optional<TrelloList> todoList = consumer.getToDoList(config.getBoardId());
		
		tCard.setIdList(todoList.orElseThrow(() -> new MissingItemAtTrelloBoard("To do list doesnt exists")).getId());
		String responseCardId = producer.createCard(config.getBoardId(), tCard);
		return responseCardId;
		}


	public String getType() {	
		return TaskService.BUG_TYPE;
	}

}
