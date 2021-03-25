package com.unifiedpost.myid.userservice.usecases;

import com.unifiedpost.myid.userservice.entities.Dossier;
import com.unifiedpost.myid.userservice.entities.User;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;

public class CreateDossierUseCase {

  private SignicatAdapter signicatAdapter;

  public CreateDossierUseCase(SignicatAdapter signicatAdapter) {
    this.signicatAdapter = signicatAdapter;
  }

  public Dossier execute(User user) {
    if (user == null) {
      return signicatAdapter.createDossier();
    } else {
      return signicatAdapter.getDossier(user.getDossierId());
    }
  }
}
