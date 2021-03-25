package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "The process information process with onfido provider")
@Getter
@Setter
public class OnfidoDetailDto extends ProcessDetailDto {

  @Schema(description = "User information")
  private UserDto user;

  public OnfidoDetailDto() {
    super.setProvider(ProviderDto.ONFIDO);
  }
}
