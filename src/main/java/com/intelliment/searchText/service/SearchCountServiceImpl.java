package com.intelliment.searchText.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliment.searchText.exception.SearchCounterApplicationException;
import com.intelliment.searchText.model.Token;
import com.intelliment.searchText.repository.CounterRepository;

@Service("searchService")
public class SearchCountServiceImpl implements SearchCountService {

	@Autowired
	CounterRepository counterRepository;
	@Override
	public Map<String, Integer> search(List<String> searchList) throws SearchCounterApplicationException {
		
		Map<String,Token> word2freq = counterRepository.getFrequencyMap();
		Map<String, Integer> m = new HashMap<String, Integer>();
		for(String s : searchList) {
			Token t = word2freq.get(s.toLowerCase());
			if(t==null) {
				m.put(s, 0);
			}else {
				m.put(s, t.getFrequency().getFrequency());
			}
			
		}
		return m;
	}
	@Override
	public List<Token> getTopNFrequentWords(int n) throws SearchCounterApplicationException{
		List<Token> sortedList = counterRepository.getSortedWordList();
		return sortedList.subList(0, n> sortedList.size()?sortedList.size():n);
	}
	
	

	
	
	
	
}
