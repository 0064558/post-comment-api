package com.example.postcommentapi.resources.exception;

import com.example.postcommentapi.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Object not found";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error, message, path);
        return ResponseEntity.status(status).body(err);
    }
}
