package br.com.bluetask.app.resources.exceptions;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.bluetask.app.services.exceptions.DuplicateTaskException;
import br.com.bluetask.app.services.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class WebRequestExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handleException(RepositoryConstraintViolationException e) {
		return RestResponseError.fromValidationError(e.getErrors());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handleException(DuplicateTaskException e) {
		return RestResponseError.fromMessage(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public RestResponseError handleException(ResourceNotFoundException e) {
		return RestResponseError.fromMessage(e.getMessage());
	}
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<RestResponseError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		RestResponseError error = new RestResponseError();		
//		error.setError(e.getMessage());		
//		return ResponseEntity.status(status).body(error);
//	}
	
}
