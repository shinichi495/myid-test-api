package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessRequestDto {
  private ProviderDto provider;
  private ProcessTypeDto processType;
  private ProcessParameterDto processParameters;
}
