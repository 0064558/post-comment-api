package com.example.postcommentapi.resources.exception;

import com.example.postcommentapi.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// Classe para tratar as exceções de forma global, ou seja, para todas as classes de recurso. Ela intercepta as exceções lançadas e retorna uma resposta personalizada com informações sobre o erro. Neste caso, ela trata a exceção ObjectNotFoundException, que é lançada quando um objeto não é encontrado no banco de dados. A resposta inclui o status HTTP 404 (Not Found), uma mensagem de erro e o caminho da requisição que causou o erro.
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
