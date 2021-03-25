package com.unifiedpost.myid.userservice.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacetecMatchingDto {
  private String matchLevel;
  private String success;
  private String error;
}
