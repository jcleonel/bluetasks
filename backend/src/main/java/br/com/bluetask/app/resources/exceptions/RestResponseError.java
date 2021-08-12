package br.com.bluetask.app.resources.exceptions;

import java.io.Serializable;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import lombok.Getter;
import lombok.Setter;

public class RestResponseError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String error;
	
	public RestResponseError() {
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
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError resp = new RestResponseError();
		resp.error = message;
		return resp;
	}
}
