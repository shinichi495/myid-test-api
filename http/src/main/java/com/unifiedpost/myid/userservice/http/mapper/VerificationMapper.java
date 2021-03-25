package com.unifiedpost.myid.userservice.http.mapper;

import com.unifiedpost.myid.userservice.entities.FacetecEnrollment;
import com.unifiedpost.myid.userservice.entities.VerificationResult;
import com.unifiedpost.myid.userservice.http.dto.FacetecEnrollmentRequest;
import com.unifiedpost.myid.userservice.http.dto.VerificationResultDto;
import com.unifiedpost.myid.userservice.http.dto.VerifyRequestDto;

public class VerificationMapper {

  public static VerifyRequestDto convert(FacetecEnrollment entity) {
    if (entity == null) {
      return null;
    } else {
      VerifyRequestDto verify = new VerifyRequestDto();
      FacetecEnrollmentRequest facetecEnrollment = new FacetecEnrollmentRequest();
      Converter.convert(entity, facetecEnrollment);
      verify.setFacetecEnrollmentRequest(facetecEnrollment);
      return verify;
    }
  }

  public static VerificationResult toEntity(VerificationResultDto dto) {
    if (dto == null) {
      return null;
    } else {
      VerificationResult entity = new VerificationResult();
      Converter.convert(dto, entity);
      return entity;
    }
  }
}
