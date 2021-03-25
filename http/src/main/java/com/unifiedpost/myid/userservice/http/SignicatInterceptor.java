package com.unifiedpost.myid.userservice.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.IOException;

@Service
@Lazy
public class SignicatInterceptor implements ClientHttpRequestInterceptor {

  @Autowired
  private TokenService tokenService;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {
    ClientHttpResponse response = execution.execute(request, body);
    if (response.getStatusCode() == HttpStatus.UNAUTHORIZED || response.getStatusCode() == HttpStatus.BAD_GATEWAY) {
      String accessToken = tokenService.getAccessToken();
      if (StringUtils.hasText(accessToken)) {
        request.getHeaders().remove(HttpHeaders.AUTHORIZATION);
        request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = execution.execute(request, body);
      }
    }

    return response;
  }
}
