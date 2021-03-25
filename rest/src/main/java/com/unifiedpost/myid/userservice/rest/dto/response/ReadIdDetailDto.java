package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Schema(description = "The process information process with readid provider")
@Getter
@Setter
public class ReadIdDetailDto extends ProcessDetailDto {

  @Schema(description = "User information")
  private UserDto user;

  public ReadIdDetailDto() {
    super.setProvider(ProviderDto.READID);
  }
}
