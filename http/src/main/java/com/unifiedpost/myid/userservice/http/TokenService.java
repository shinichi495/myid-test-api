package com.unifiedpost.myid.userservice.http;

import com.unifiedpost.myid.userservice.http.dto.AccessTokenDto;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Service
@Lazy
@Slf4j
public class TokenService {

  public static String GRANT_TYPE = "client_credentials";
  public static String SCOPE = "client.assure.api client.capture.api";

  @Autowired
  private RestTemplate restTemplate;

  @Value("${signicat.server.url}" + "/oidc/token")
  private String signicatServerUrl;

  @Value("${signicat.secret.key}")
  private String signicatSecretKey;

  public String getAccessToken() {
    // TODO: cache access token
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set("Authorization", "Basic " + signicatSecretKey);

    String body = buildRequestBody();
    ResponseEntity<AccessTokenDto> tokenDto = restTemplate.exchange(signicatServerUrl, HttpMethod.POST, new HttpEntity<>(body, headers), AccessTokenDto.class);
    return tokenDto.getBody().getAccessToken();
  }

  private String buildRequestBody() {
    Map<String, String> body = new HashMap<>();
    body.put("grant_type", GRANT_TYPE);
    body.put("scope", SCOPE);

    String bodyUrlencoded = body.entrySet().stream().map(entry -> {
      try {
        return entry.getKey() + "="
            + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name());
      } catch (UnsupportedEncodingException e) {
        log.error(e.getMessage(), e);
      }
      return "";
    }).collect(Collectors.joining("&", "", ""));

    return bodyUrlencoded;
  }
}
