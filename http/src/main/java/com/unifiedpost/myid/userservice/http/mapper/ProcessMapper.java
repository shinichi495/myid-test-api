package com.unifiedpost.myid.userservice.http.mapper;

import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.*;
import com.unifiedpost.myid.userservice.http.dto.ProcessResponseDto;
import com.unifiedpost.myid.userservice.http.dto.ProviderSpecificResponse;

public class ProcessMapper {

  public static Process toEntity(ProcessResponseDto dto) {
    if (dto == null) {
      return null;
    }
    switch (dto.getProvider()) {
      case FACETEC: {
        FacetecProcess faceTec = new FacetecProcess();
        Converter.convert(dto, faceTec);
        ProviderSpecificResponse specific = dto.getProviderSpecific();
        if (specific != null && specific.getMatching() != null) {
          FacetecMatching matching = new FacetecMatching();
          Converter.convert(specific.getMatching(), matching);
          faceTec.setMatching(matching);
        }
        return faceTec;
      }
      case ONFIDO: {
        OnfidoProcess onfido = new OnfidoProcess();
        Converter.convert(dto, onfido);
        if (dto.getFinalResult() != null) {
          User user = new User();
          Converter.convert(dto.getFinalResult(), user);
          onfido.setUser(user);
        }
        return onfido;
      }
      case READID: {
        ReadIdProcess readId = new ReadIdProcess();
        Converter.convert(dto, readId);
        if (dto.getFinalResult() != null) {
          User user = new User();
          Converter.convert(dto.getFinalResult(), user);
          readId.setUser(user);
        }
        return readId;
      }
      default:
        return null;
    }
  }
}
