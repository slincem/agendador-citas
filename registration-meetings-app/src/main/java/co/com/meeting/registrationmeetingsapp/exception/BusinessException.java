package co.com.meeting.registrationmeetingsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 6063420543698939857L;

	public BusinessException(String message, Exception generatedException) {
		super(message, generatedException);
	}
	
	public BusinessException(String message) {
		super(message);
	}

}
