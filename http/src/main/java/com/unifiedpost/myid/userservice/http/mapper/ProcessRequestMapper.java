package com.unifiedpost.myid.userservice.http.mapper;

import com.unifiedpost.myid.userservice.entities.ProcessRequest;
import com.unifiedpost.myid.userservice.http.dto.ProcessParameterDto;
import com.unifiedpost.myid.userservice.http.dto.ProcessRequestDto;
import com.unifiedpost.myid.userservice.http.dto.ProcessTypeDto;
import com.unifiedpost.myid.userservice.http.dto.ProviderDto;

public class ProcessRequestMapper {

  public static ProcessRequestDto toDto(ProcessRequest entity) {
    if (entity == null) {
      return null;
    }
    ProcessRequestDto dto = new ProcessRequestDto();
    dto.setProvider(entity.getProvider() == null ? null : ProviderDto.fromString(entity.getProvider().getValue()));
    dto.setProcessType(entity.getProcessType() == null ? null : ProcessTypeDto.fromString(entity.getProcessType().getValue()));
    if (entity.getProcessParameter() != null) {
      ProcessParameterDto processParameterDto = new ProcessParameterDto();
      processParameterDto.setMobileAppId(entity.getProcessParameter().getMobileAppId());
      dto.setProcessParameters(processParameterDto);
    }
    return dto;
  }
}
