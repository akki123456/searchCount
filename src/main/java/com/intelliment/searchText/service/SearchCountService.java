package com.intelliment.searchText.service;

import java.util.List;
import java.util.Map;

import com.intelliment.searchText.exception.SearchCounterApplicationException;
import com.intelliment.searchText.model.Token;

public interface SearchCountService {
	
	Map<String,Integer> search(List<String> searchList) throws SearchCounterApplicationException;

	List<Token> getTopNFrequentWords(int n) throws SearchCounterApplicationException;
}
