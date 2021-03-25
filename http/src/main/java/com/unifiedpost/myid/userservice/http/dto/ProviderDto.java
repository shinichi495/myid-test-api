package com.unifiedpost.myid.userservice.http.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

public enum ProviderDto {
  ONFIDO("onfido"),
  READID("readid"),
  FACETEC("facetec");

  private String value;

  ProviderDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static ProviderDto fromString(String value) {
    if (StringUtils.hasText(value)) {
      for (ProviderDto provider : ProviderDto.values()) {
        if (provider.value.equalsIgnoreCase(value)) {
          return provider;
        }
      }
    }
    return null;
  }

}
