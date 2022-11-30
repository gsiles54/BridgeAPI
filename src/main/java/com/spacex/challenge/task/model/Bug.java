package com.spacex.challenge.task.model;

import com.spacex.challenge.misc.TitleRandomizer;

public class Bug extends DescriptableTask {
	
	public Bug() {
		TitleRandomizer titleRandom = new TitleRandomizer();
		this.setTitle(titleRandom.randomizeTitle());
	}
}
