package com.spacex.challenge.task.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public void workTask(Bug task) {

		Optional<TrelloMember> randomMember = consumer.getRandomMemberFromBoard("EnpRiV5p");
		String cardId = randomMember.orElseThrow().getId();
		String[] cardIds = new String[1];
		cardIds[0] = cardId;
		
		TrelloCard tCard = new TrelloCard();
		tCard.setIdBoard("EnpRiV5p");
		tCard.setDesc(task.getDescription());
		tCard.setIdMembers(cardIds);
		tCard.setName(task.getTitle());

		Optional<TrelloLabel> bugLabel = consumer.getLabelFromBoard("EnpRiV5p",TaskService.BUG_LABEL);
		String[] labelIds = new String[1];
		labelIds[0] = bugLabel.orElseThrow().getId();;
		tCard.setIdLabels(labelIds);
		
		Optional<TrelloList> todoList = consumer.getToDoList("EnpRiV5p");
		
		tCard.setIdList(todoList.orElseThrow().getId());
		producer.createCard("EnpRiV5p", tCard);
		}


	public String getType() {	
		return TaskService.BUG_TYPE;
	}

}
