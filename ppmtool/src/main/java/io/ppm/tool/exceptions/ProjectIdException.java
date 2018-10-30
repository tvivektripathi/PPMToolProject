package io.ppm.tool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2661015068388513074L;

	public ProjectIdException(String message) {
		super(message);
	}
}
