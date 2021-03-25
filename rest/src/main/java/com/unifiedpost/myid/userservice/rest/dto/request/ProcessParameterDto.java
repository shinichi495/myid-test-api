package com.unifiedpost.myid.userservice.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessParameterDto {

  @Schema(
      description = "(Used only when the provider is 'onfido'.)",
      example = "com.onfido.sampleapp"
  )
  private String mobileAppId;
}
