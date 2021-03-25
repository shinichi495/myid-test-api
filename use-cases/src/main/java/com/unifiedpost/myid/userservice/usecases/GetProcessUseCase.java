package com.unifiedpost.myid.userservice.usecases;

import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;

public class GetProcessUseCase {

  private SignicatAdapter signicatAdapter;

  public GetProcessUseCase(SignicatAdapter signicatAdapter) {
    this.signicatAdapter = signicatAdapter;
  }

  public Process execute(String dossierId, String processId) {
    if (dossierId == null || processId == null) {
      return null;
    } else {
      return signicatAdapter.getProcess(dossierId, processId);
    }
  }
}
