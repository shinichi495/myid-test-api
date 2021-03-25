package com.unifiedpost.myid.userservice.rest.dto.response;

import com.unifiedpost.myid.userservice.rest.dto.ProviderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "The information process with provider facetec")
public class FacetecDetailDto extends ProcessDetailDto {

  private FacetecMatchingDto matching;

  public FacetecDetailDto() {
    setProvider(ProviderDto.FACETEC);
  }

}
