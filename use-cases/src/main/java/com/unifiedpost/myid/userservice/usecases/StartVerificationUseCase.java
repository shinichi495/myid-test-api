package com.unifiedpost.myid.userservice.usecases;

import com.unifiedpost.myid.userservice.entities.FacetecEnrollment;
import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.VerificationResult;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;

public class StartVerificationUseCase {

  private SignicatAdapter signicatAdapter;

  public StartVerificationUseCase(SignicatAdapter signicatAdapter) {
    this.signicatAdapter = signicatAdapter;
  }

  public VerificationResult execute(String dossierId, FacetecEnrollment factecEnrollment) {
    if (dossierId == null || factecEnrollment == null) {
      return null;
    }
    return signicatAdapter.startVerification(dossierId, factecEnrollment);
  }

}
