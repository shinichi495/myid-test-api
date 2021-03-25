package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Process created with provider facetec")
public class FacetecInitializationDto extends ProcessInitializationDto {

  @Schema(description = "", example = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5PxZ3DLj+zP6T6HFgzzkM77LdzP3fojBoLasw7EfzvLMnJNUlyRb5m8e5QyyJxI+wRjsALHvFgLzGwxM8ehzDqqBZed+f4w33GgQXFZOS4AOvyPbALgCYoLehigLAbbCNTkeY5RDcmmSI/sbp+s6mAiAKKvCdIqe17bltZ/rfEoL3gPKEfLXeN549LTj3XBp0hvG4loQ6eC1E1tRzSkfGJD4GIVvR+j12gXAaftj3ahfYxioBH7F7HQxzmWkwDyn3bqU54eaiB7f0ftsPpWMceUaqkL2DZUvgN0efEJjnWy5y1/Gkq5GGWCROI9XG/SwXJ30BbVUehTbVcD70+ZF8QIDAQAB-----END PUBLIC KEY-----")
  private String facetecEncryptionPublicKey;
  @Schema(example = "dvFMSBMcyjjUB7dvR0lFWsOHoNiBsLDh")
  private String facetecDeviceKeyIdentifier;

  public FacetecInitializationDto() {
    setProvider(ProviderDto.FACETEC);
  }


}
