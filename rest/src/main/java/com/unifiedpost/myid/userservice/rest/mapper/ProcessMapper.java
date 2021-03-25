package com.unifiedpost.myid.userservice.rest.mapper;

import com.unifiedpost.myid.userservice.entities.*;
import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.rest.dto.*;
import com.unifiedpost.myid.userservice.rest.dto.response.*;

public class ProcessMapper {

  public static ProcessInitializationDto toInitializationDto(Process entity) {
    if (entity != null) {
      if (entity instanceof FacetecProcess) {
        FacetecProcess faceTec = (FacetecProcess) entity;
        FacetecInitializationDto faceTecDto = new FacetecInitializationDto();
        Converter.convert(faceTec, faceTecDto);
        return faceTecDto;
      } else {
        Provider provider = entity.getProvider();
        if (provider == Provider.ONFIDO || provider == Provider.READID) {
          ProcessInitializationDto processDto = new ProcessInitializationDto();
          processDto.setProvider(ProviderDto.fromString(provider.getValue()));
          Converter.convert(entity, processDto);
          return processDto;
        }
      }
    }
    return null;
  }

  public static ProcessDetailDto toDetailDto(Process entity) {
    if (entity != null) {
      if (entity instanceof FacetecProcess) {
        FacetecProcess faceTec = (FacetecProcess) entity;
        FacetecDetailDto faceTecDto = new FacetecDetailDto();
        Converter.convert(faceTec, faceTecDto);
        return faceTecDto;
      } else if (entity instanceof OnfidoProcess) {
        OnfidoProcess onfidoEntity = (OnfidoProcess) entity;
        OnfidoDetailDto onfidoDto = new OnfidoDetailDto();
        Converter.convert(onfidoEntity, onfidoDto);
        return onfidoDto;
      } else if (entity instanceof ReadIdProcess) {
        ReadIdProcess readIdEntity = (ReadIdProcess) entity;
        ReadIdDetailDto readIdDto = new ReadIdDetailDto();
        Converter.convert(readIdEntity, readIdDto);
        return readIdDto;
      }
    }
    return null;
  }
}
