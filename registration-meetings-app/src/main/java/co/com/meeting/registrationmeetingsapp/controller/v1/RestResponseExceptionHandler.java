package co.com.meeting.registrationmeetingsapp.controller.v1;

import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        log.error("Resource not found", exception);
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(Exception exception, WebRequest webRequest) {
        log.error("Business exception", exception);
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
