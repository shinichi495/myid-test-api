package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResultDto {
  private String processId;
  private String status;
}
