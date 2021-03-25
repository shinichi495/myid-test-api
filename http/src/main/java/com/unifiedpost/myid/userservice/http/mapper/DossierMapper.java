package com.unifiedpost.myid.userservice.http.mapper;

import com.unifiedpost.myid.userservice.entities.Dossier;
import com.unifiedpost.myid.userservice.http.dto.DossierDto;

public class DossierMapper {

  public static Dossier toEntity(DossierDto dto) {
    if (dto == null) {
      return null;
    }
    Dossier entity = new Dossier();
    entity.setDossierId(dto.getDossierId());
    return entity;
  }
}
