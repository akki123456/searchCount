package com.intelliment.searchText.repository;

import java.util.List;
import java.util.Map;

import com.intelliment.searchText.exception.SearchCounterApplicationException;
import com.intelliment.searchText.model.Token;

public interface CounterRepository {

	public Map<String,Token> getFrequencyMap() throws SearchCounterApplicationException;
	
	public List<Token> getSortedWordList() throws SearchCounterApplicationException;
}
