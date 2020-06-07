package com.mitesh.cloud.techlearn.hospitalmanagement.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorResource() {
		super();
	}
	public ErrorResource(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
