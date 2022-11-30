package com.spacex.challenge.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.spacex.challenge.task.exception.MissingItemAtTrelloBoard;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
	 
	@ExceptionHandler(IllegalStateException.class)
		    protected ResponseEntity<Object> handleIllegalStateConflict(
		    		IllegalStateException ex, WebRequest request) {
		        String bodyOfResponse = "The provided json value for \"type\" is not valid. No task has been created.";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentConflict(
    		IllegalArgumentException ex, WebRequest request) {
        String bodyOfResponse = "The provided json value for \"type\" is not valid. No task has been created. " + ex.getMessage() ;
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
	
	@ExceptionHandler(UnrecognizedPropertyException.class)
		    protected ResponseEntity<Object> handlePropertyConflict(
		    		UnrecognizedPropertyException ex, WebRequest request) {
		        String bodyOfResponse = "The provided json structure is invalid. No task has been created.";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		    }
	
	@ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<Object> handleJsonProcessingConflict(
    		JsonProcessingException ex, WebRequest request) {
        String bodyOfResponse = "An error has ocurred while processing the json data. Please contact administrator";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
	
    @ExceptionHandler(MissingItemAtTrelloBoard.class)
    protected ResponseEntity<Object> handleTrelloProcessingConflict(
    		MissingItemAtTrelloBoard ex, WebRequest request) {
        String bodyOfResponse = "An error has ocurred while processing the json data. Please contact administrator";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
