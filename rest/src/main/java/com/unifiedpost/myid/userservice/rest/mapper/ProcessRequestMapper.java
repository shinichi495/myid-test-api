package com.unifiedpost.myid.userservice.rest.mapper;

import com.unifiedpost.myid.userservice.entities.ProcessParameter;
import com.unifiedpost.myid.userservice.entities.ProcessRequest;
import com.unifiedpost.myid.userservice.entities.ProcessType;
import com.unifiedpost.myid.userservice.entities.Provider;
import com.unifiedpost.myid.userservice.rest.dto.request.IdProofingRequestDto;
import com.unifiedpost.myid.userservice.rest.dto.request.ProcessRequestDto;

public class ProcessRequestMapper {

  public static ProcessRequest toEntity(ProcessRequestDto dto) {
    if (dto == null) {
      return null;
    } else {
      ProcessRequest entity = new ProcessRequest();
      entity.setProvider(Provider.fromString(dto.getProvider().getValue()));
      return entity;
    }
  }

  public static ProcessRequest convert(IdProofingRequestDto dto) {
    if (dto == null) {
      return null;
    } else {
      ProcessRequest request = new ProcessRequest();
      request.setProvider(Provider.fromString(dto.getProvider().getValue()));
      request.setProcessType(ProcessType.fromString(dto.getProcessType().getValue()));
      ProcessParameter parameter = ProcessParameterMapper.toEntity(dto.getProcessParameters());
      request.setProcessParameter(parameter);
      return request;
    }
  }
}
