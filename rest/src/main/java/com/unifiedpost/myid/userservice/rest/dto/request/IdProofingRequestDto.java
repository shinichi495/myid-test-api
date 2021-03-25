package com.unifiedpost.myid.userservice.rest.dto.request;

import com.unifiedpost.myid.userservice.rest.dto.ProcessTypeDto;
import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IdProofingRequestDto {

  @Schema(description = "The unique email to identify whose created dossier belong to", example = "abc@mail.com")
  @NotNull
  @NotEmpty
  @Email
  private String email;

  @Schema
  @NotNull
  @Digits(integer = 15, fraction = 0, message = "Must be digits and not be longer than 15 digits")
  private String phoneNumber;

  @Schema(description = "The provider used in the identity document verification.", example = "onfido",
      required = true)
  @NotNull
  private ProviderDto provider;

  @Schema(example = "document", required = true)
  @NotNull
  private ProcessTypeDto processType;

  @Schema(
      description = "Parameters specific for using with each provider",
      required = true)
  @NotNull
  private ProcessParameterDto processParameters;

}
