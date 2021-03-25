package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessResponseDto {

  private String processId;
  private ProviderDto provider;
  private String status;
  private String authorization;
  private String facetecEncryptionPublicKey;
  private String facetecDeviceKeyIdentifier;
  private FinalResultProcessDto finalResult;
  private ProviderSpecificResponse providerSpecific;

}
