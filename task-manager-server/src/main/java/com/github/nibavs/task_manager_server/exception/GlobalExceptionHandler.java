package com.github.nibavs.task_manager_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleSellerNotFound(UserNotFoundException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "Auth failed!");
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
