package com.intelliment.searchText.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intelliment.searchText.exception.FileReaderException;
import com.intelliment.searchText.exception.SearchCounterApplicationException;
import com.intelliment.searchText.model.Token;

@Repository("counterRepository")
public class CachedSingleReadCounterRepositoryImpl implements CounterRepository {
	
	@Autowired
	ParagraphReader paragraphReader;
	boolean mapPopulated = false;
	
	Map<String,Token> word2freq = new HashMap<String,Token>();
	List<Token> sortedList = null;

	public Map<String, Token> getFrequencyMap() throws SearchCounterApplicationException {
		if(!mapPopulated) {
			populateMap();
		}
		return word2freq;
	}
	
	public List<Token> getSortedWordList() throws SearchCounterApplicationException{
		if(sortedList==null) {
			try {
			if(!mapPopulated) {
				populateMap();
			}
			sortedList = new ArrayList<Token>();
			for(Entry<String,Token> w : word2freq.entrySet()) {
			sortedList.add(w.getValue());
			}
			sortedList.sort((t1 ,t2) -> t2.getFrequency().getFrequency()-t1.getFrequency().getFrequency());
			}catch(RuntimeException e) {
				sortedList= null;
				throw e;
			}
		}
		return sortedList;
	}

	
	 void populateMap() throws SearchCounterApplicationException {
		BufferedReader br = new BufferedReader(paragraphReader.getParaReader());
		String line = null;
		try {
		while((line=br.readLine())!=null) {
			String content = line.replaceAll("[^A-Za-z0-9 ]", " ").toLowerCase();
			String[] contentArray = content.split("\\s+");
			 addWordsToMap(contentArray, word2freq);
		}
		}catch(IOException ioe) {
			throw new FileReaderException(ioe.getMessage());
		}
		mapPopulated=true;
	}

	
	 private void addWordsToMap(String[] contentArray, Map<String, Token> wordMap) {
		for (String word : contentArray) {
			Token token  = wordMap.get(word);
		     if(token!=null){
		         token.incrementFrequency();
		     }else {
		          token = new Token(word);
		         wordMap.put(word,token);
		     }
		     } 
		   }
}
