package com.unifiedpost.myid.userservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdProofingResponseDto {

  @Schema(example = "37191afe-25b3-4c30-b42a-b0ab64aa1d64")
  private String dossierId;

  @Schema(description = "Process created with parameters from body request")
  private ProcessInitializationDto process;

}
