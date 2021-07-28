package br.com.bluetask.app.infrastructure.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import lombok.Getter;

public class RestResponseError {
	
	@Getter
	private String error;
	
	private RestResponseError() {
	}
	
	public static RestResponseError fromValidationError(Errors errors) {
		RestResponseError resp = new RestResponseError();
		StringBuilder sb = new StringBuilder();
		
		for (ObjectError error : errors.getAllErrors()) {
			sb.append(error.getDefaultMessage()).append(". ");
		}
		
		resp.error = sb.toString();
		return resp;
	}

}
