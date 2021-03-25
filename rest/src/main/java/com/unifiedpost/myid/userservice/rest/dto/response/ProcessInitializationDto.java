package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Process created with provider onfido or readid")
public class ProcessInitializationDto {

  @Schema(description = "The unique identifier of a process.", example = "710a4326-ef7f-4bed-85c4-aa523f742742")
  private String processId;

  @Schema(description = "The provider used in the identity document verification.")
  private ProviderDto provider;

  @Schema(description = "The current status of the the identity verification process.",
      allowableValues = {"pending", "processing", "accepted", "rejected", "inconclusive", "canceled"})
  private String status;

  @Schema(description = "The authorization token that is necessary for uploading images using the provider's SDK. "
      + "(This is not necessary if the images are uploaded directly through the API, "
      + "using the Set Images service",
      example = "eyJhbGciOiJJ9.eyJwYXlsb2FYVjRGVhZU252alVsekNHaUMzR9.uW-2dEu_rYUyhF_E"
  )
  private String authorization;

}
