package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessDetailDto {

  @Schema(description = "The unique identifier of a process.")
  private String processId;
  @Schema(description = "The provider used in the identity document verification.")
  private ProviderDto provider;
  @Schema(description = "The current status of the the identity verification process.",
      allowableValues = {"pending", "processing", "accepted", "rejected", "inconclusive", "canceled"})
  private String status;
}
