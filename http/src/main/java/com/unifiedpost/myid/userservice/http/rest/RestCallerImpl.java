package com.unifiedpost.myid.userservice.http.rest;

import com.unifiedpost.myid.userservice.http.SignicatInterceptor;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Lazy
public class RestCallerImpl<T, B> implements RestCaller {

  @Autowired
  private SignicatInterceptor signicatInterceptor;

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Call for an operation
   *
   * @param url           : url to access 3rd party
   * @param method        : HTTP method to call (GET, POST, PUT, ...)
   * @param uriVariables: optional request parameters
   * @param body:         request body
   * @param responseType
   * @return
   */


  @Override
  public <T, B> ResponseEntity<T> call(String url, HttpMethod method, B body, Class<T> responseType, Object... uriVariables) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    restTemplate.setInterceptors(Collections.singletonList(signicatInterceptor));
    return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), responseType,
        uriVariables);
  }

}
