package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacetecMatchingResponse {
  private String matchLevel;
  private String success;
  private String error;
}
