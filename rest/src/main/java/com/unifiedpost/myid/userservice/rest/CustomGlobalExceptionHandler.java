package com.unifiedpost.myid.userservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifiedpost.myid.userservice.rest.dto.response.ResponseExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.error(ex.getMessage(), ex);
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", status.value());
    body.put("message", "validation error");
    // Get all errors
    Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
            (x, y) -> x + ", " + y, HashMap::new));
    body.put("errors", errors);
    body.put("path", ServletUriComponentsBuilder.fromCurrentRequest().build().getPath());

    return new ResponseEntity<>(body, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.error(ex.getMessage(), ex);
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", status.value());
    body.put("title", "Validation failed");
    body.put("errors", ex.getMessage());
    body.put("path", ServletUriComponentsBuilder.fromCurrentRequest().build().getPath());

    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(ResponseStatusException.class)
  protected ResponseEntity<?> handleStatusException(ResponseStatusException ex) {
    logger.error(ex.getReason(), ex);
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", ex.getStatus().value());
    body.put("title", ex.getReason());
    body.put("path", ServletUriComponentsBuilder.fromCurrentRequest().build().getPath());
    return new ResponseEntity<>(body, ex.getStatus());
  }

  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<?> handleClientException(HttpClientErrorException ex, WebRequest request) {
    logger.error(ex.getResponseBodyAsString());
    String messageError = ex.getResponseBodyAsString();
    try {
      ResponseExceptionDto exceptionDto = new ObjectMapper().readValue(messageError, ResponseExceptionDto.class);
      return new ResponseEntity<>(exceptionDto, ex.getStatusCode());
    } catch (JsonProcessingException e) {
      logger.error(e);
      return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }
  }
}
