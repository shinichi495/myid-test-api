package com.unifiedpost.myid.userservice.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenDto {

  @JsonProperty("access_token")
  private String accessToken;
}
