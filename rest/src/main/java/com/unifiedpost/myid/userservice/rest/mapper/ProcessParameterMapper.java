package com.unifiedpost.myid.userservice.rest.mapper;

import com.unifiedpost.myid.userservice.entities.ProcessParameter;
import com.unifiedpost.myid.userservice.rest.dto.request.ProcessParameterDto;
import org.springframework.beans.BeanUtils;

public class ProcessParameterMapper {

  public static ProcessParameter toEntity(ProcessParameterDto dto) {
    if (dto == null) {
      return null;
    }
    ProcessParameter entity = new ProcessParameter();
    BeanUtils.copyProperties(dto, entity);

    return entity;
  }
}
