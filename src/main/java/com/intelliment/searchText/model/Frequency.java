package com.intelliment.searchText.model;

public class Frequency {

    public Frequency(){
	    this.count = 1;
	}

	private int count;

	public int getFrequency(){
	    return this.count;
	}

	public void incrementFrequency(){
	    this.count++;
	}
}
