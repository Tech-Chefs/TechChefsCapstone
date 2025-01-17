package learn.techchefs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // TODO log this exception

        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}