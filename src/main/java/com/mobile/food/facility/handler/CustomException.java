package com.mobile.food.facility.handler;

import org.springframework.stereotype.Component;

import com.mobile.food.facility.dto.response.StatusMessage;

@Component
public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private StatusMessage statusMessage;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String s) {
		super(s);
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public StatusMessage getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(StatusMessage statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}

