package com.intelliment.searchText.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelliment.searchText.exception.BadRequestException;
import com.intelliment.searchText.exception.SearchCounterApplicationException;
import com.intelliment.searchText.model.SearchList;
import com.intelliment.searchText.model.Token;
import com.intelliment.searchText.service.SearchCountService;

@RestController
public class SearchController {

	@Autowired
    private SearchCountService searchService;

	
    @RequestMapping(value="/search", method= RequestMethod.POST )
    public Map<String,List<Map<String,Integer>>> search(@RequestBody SearchList searchList) throws SearchCounterApplicationException  { 	
    	Map<String,List<Map<String,Integer>>> res = new HashMap<String,List<Map<String,Integer>>>();
    	Map<String,Integer> m = searchService.search(searchList.getSearchText());
    	List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>();
    	for(Entry<String,Integer> e : m.entrySet()) {
    		list.add(Collections.singletonMap(e.getKey(), e.getValue()));
    	}
    	res.put("counts", list);
       return res;
    }
    
    @RequestMapping(value="/top/{n}", method= RequestMethod.GET ,produces="text/csv")
    public String top(@PathVariable("n") String n) throws SearchCounterApplicationException  {
    	int topN =0;
    	try {
    	 topN = Integer.parseInt(n);
    	}catch(Exception e) {
    		
    		throw new BadRequestException("Number not correct");
    	}
    	if(topN<=0) {
    		throw new BadRequestException("Number should be positive");
    	}
    	List<Token> sortedList = searchService.getTopNFrequentWords(topN);
    	StringBuilder sb = new StringBuilder();
    	for(Token t : sortedList) {
    		sb.append(t.getWord()+"|"+t.getFrequency().getFrequency());
    		sb.append("\n");
    	}
       return sb.toString();
    }
	
}
