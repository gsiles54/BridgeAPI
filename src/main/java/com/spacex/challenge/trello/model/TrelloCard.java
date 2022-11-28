package com.spacex.challenge.trello.model;

public class TrelloCard {
	private String name;
	private String desc;
	private String idList;
	private String[] idMembers;
	private String[] idLabels;
	private String idBoard;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

	public String getIdBoard() {
		return idBoard;
	}
	public void setIdBoard(String idBoard) {
		this.idBoard = idBoard;
	}
	public String[] getIdMembers() {
		return idMembers;
	}
	public void setIdMembers(String[] idMembers) {
		this.idMembers = idMembers;
	}
	public String[] getIdLabels() {
		return idLabels;
	}
	public void setIdLabels(String[] idLabels) {
		this.idLabels = idLabels;
	}
	
}
