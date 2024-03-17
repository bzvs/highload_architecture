package ru.bzvs.higharc.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bzvs.higharc.exception.CounterException;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Void> handleNotFound(CounterException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
