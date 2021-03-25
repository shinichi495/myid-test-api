package com.unifiedpost.myid.userservice.rest.mapper;

import com.unifiedpost.myid.userservice.entities.Dossier;
import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.rest.dto.response.IdProofingResponseDto;
import com.unifiedpost.myid.userservice.rest.dto.response.ProcessInitializationDto;

public class IdProofingResponseMapper {

  public static IdProofingResponseDto toDto(Dossier dossier, Process process) {
    if (dossier == null || process == null) {
      return null;
    }
    IdProofingResponseDto dto = new IdProofingResponseDto();
    dto.setDossierId(dossier.getDossierId());
    ProcessInitializationDto processDto = ProcessMapper.toInitializationDto(process);
    dto.setProcess(processDto);
    return dto;
  }
}
