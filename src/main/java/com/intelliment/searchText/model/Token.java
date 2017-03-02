package com.intelliment.searchText.model;

public class Token {

	String word;
	Frequency frequency;
	
	public Token(String word) {
		this.word= word;
		this.frequency = new Frequency();
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Frequency getFrequency() {
		return frequency;
	}
	public void incrementFrequency() {
		frequency.incrementFrequency();
	}
	
}
