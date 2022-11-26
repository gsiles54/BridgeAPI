package com.spacex.challenge.taskmodel;

public abstract class DescriptableTask extends Task{
	protected String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
