package com.unifiedpost.myid.userservice.http.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RestCaller {

  <T, B> ResponseEntity<T> call(String url, HttpMethod method,
                                B body, Class<T> responseType, Object... uriVariables);
}
