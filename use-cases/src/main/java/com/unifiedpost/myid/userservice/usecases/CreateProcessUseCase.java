package com.unifiedpost.myid.userservice.usecases;

import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.ProcessRequest;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;

public class CreateProcessUseCase {

  private SignicatAdapter signicatAdapter;

  public CreateProcessUseCase(SignicatAdapter signicatAdapter) {
    this.signicatAdapter = signicatAdapter;
  }

  public Process execute(String dossierId, ProcessRequest processRequest) {
    if (processRequest == null || dossierId == null) {
      return null;
    } else {
      return signicatAdapter.createProcess(dossierId, processRequest);
    }
  }
}
