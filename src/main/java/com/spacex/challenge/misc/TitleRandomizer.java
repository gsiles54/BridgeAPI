package com.spacex.challenge.misc;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TitleRandomizer implements ITitleRandomizer {
	
	Random random ;
	
	public TitleRandomizer() {
		random = new Random();
	}
	@Override
	public String randomizeTitle() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("bug-");
		strBuilder.append(generateRandomWord());
		strBuilder.append("-");
		strBuilder.append(generateRandomNumber());
		
		return strBuilder.toString();
	}
	
	private String generateRandomWord() {
		 char[] word = new char[6];
		    for(int i = 0; i < word.length; i++)
		    {
		    	word[i] = (char)('a' + random.nextInt(26));

		    }
		    return new String(word);
	}
	
	private int generateRandomNumber() {
		return random.nextInt(Integer.MAX_VALUE);
	}

}
