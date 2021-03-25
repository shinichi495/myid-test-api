package com.unifiedpost.myid.userservice.rest.mapper;

import com.unifiedpost.myid.userservice.entities.FacetecEnrollment;
import com.unifiedpost.myid.userservice.rest.dto.request.FacetecEnrollmentRequestDto;

public class FactecEnrollmentMapper {

  public static FacetecEnrollment toEntity(FacetecEnrollmentRequestDto dto) {
    if (dto == null) {
      return null;
    } else {
      FacetecEnrollment entity = new FacetecEnrollment();
      Converter.convert(dto, entity);
      return entity;
    }
  }
}
