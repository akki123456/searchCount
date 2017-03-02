package com.intelliment.searchText;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.intelliment.searchText.model.SearchList;
 

public class RestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/counter-api";
    
    private static HttpHeaders getHeaders(String acceptHeader){
    	String plainCredentials="admin:admin";
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
 
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	if(acceptHeader!=null) {
    		headers.add("Accept",acceptHeader);
    	}
    	
    	return headers;
    }
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void getTopWords(){
        System.out.println("Testing getTopWords API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders("text/csv"));
        ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/top/5",
        		HttpMethod.GET, request, String.class);
       String  result = (String) response.getBody();
       System.out.println(result);
        
         
    }
     
     
    /* POST */
    @SuppressWarnings("unchecked")
    private static void searchWords() {
        RestTemplate restTemplate = new RestTemplate();
        SearchList searchList = new SearchList();
        searchList.setSearchText(Collections.singletonList("Sed"));
        HttpEntity<Object> request = new HttpEntity<Object>(searchList,getHeaders("text/csv"));
        ResponseEntity<Map> response = restTemplate.exchange(REST_SERVICE_URI+"/search", HttpMethod.POST,
        		request, Map.class);
         Map<String,List<Map<String,Integer>>> m = (Map<String,List<Map<String,Integer>>>)response.getBody();
        System.out.println(m);
        
    }
 
 
    public static void main(String args[]){
       searchWords();
       getTopWords();
    }
}