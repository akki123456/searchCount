package com.intelliment.searchText.repository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.intelliment.searchText.service.SearchCountServiceImpl;

@Repository("paragraphReader")
public class LocalFileParaReader implements ParagraphReader{

	@Value("${para.filePath}")
	String filePath ;
	
	public Reader getParaReader() {
		InputStream is = SearchCountServiceImpl.class.getResourceAsStream(filePath);
		Reader reader = new InputStreamReader(is);
		return reader;
	}

}
