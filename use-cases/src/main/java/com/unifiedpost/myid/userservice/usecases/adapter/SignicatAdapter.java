package com.unifiedpost.myid.userservice.usecases.adapter;

import com.unifiedpost.myid.userservice.entities.*;
import com.unifiedpost.myid.userservice.entities.Process;

public interface SignicatAdapter {

  Dossier createDossier();

  Dossier getDossier(String dossierId);

  Process createProcess(String dossierId, ProcessRequest processRequest);

  Process getProcess(String dossierId, String processId);

  VerificationResult startVerification(String dossierId, FacetecEnrollment factecEnrollment);

}
