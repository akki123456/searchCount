package com.intelliment.searchText.exception;

public class SearchCounterApplicationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7042617618554961578L;
	private String errorMessage;

    public SearchCounterApplicationException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
