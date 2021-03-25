package com.unifiedpost.myid.userservice.rest.dto.request;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProcessRequestDto {

  @Schema(description = "The provider used in the identity document verification.", required = true)
  @NotNull
  private ProviderDto provider;

}
