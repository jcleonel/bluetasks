package br.com.bluetask.app.services.exceptions;

public class DuplicateTaskException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	public DuplicateTaskException(String message) {
		super(message);
	}
	
}
