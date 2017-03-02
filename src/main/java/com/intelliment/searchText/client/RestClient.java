package com.intelliment.searchText.client;



import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

public class RestClient {
	
	public static void main(String args[]) {
		getHeaders();
	}
	 private static HttpHeaders getHeaders(){
	        String plainCredentials="admin:admin";
	        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	         
	        HttpHeaders headers = new HttpHeaders();
	        System.out.println(base64Credentials);
	        headers.add("Authorization", "Basic " + base64Credentials);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        return headers;
	    }
}

