package com.spacex.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacex.challenge.misc.ITitleRandomizer;

@SpringBootTest
public class TitleTest {
	@Autowired
	ITitleRandomizer titleRandomizer;
	
	@Test
	public void generateRandomTitleTest() {
		String firstTitle = titleRandomizer.randomizeTitle();
		String secondTitle = titleRandomizer.randomizeTitle();
		
		assertThat(!firstTitle.equals(secondTitle));
	}
}
