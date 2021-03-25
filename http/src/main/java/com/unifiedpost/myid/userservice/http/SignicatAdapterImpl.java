package com.unifiedpost.myid.userservice.http;

import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.*;
import com.unifiedpost.myid.userservice.http.dto.*;
import com.unifiedpost.myid.userservice.http.mapper.DossierMapper;
import com.unifiedpost.myid.userservice.http.mapper.ProcessMapper;
import com.unifiedpost.myid.userservice.http.mapper.ProcessRequestMapper;
import com.unifiedpost.myid.userservice.http.mapper.VerificationMapper;
import com.unifiedpost.myid.userservice.http.rest.RestCaller;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

@Slf4j
public class SignicatAdapterImpl implements SignicatAdapter {

  @Value("${signicat.server.url}" + "/assure")
  private String signicatAssureUrl;

  @Autowired
  private RestCaller restCaller;

  @Override
  public Dossier createDossier() {
    log.info("Request to create dossier in signicat");
    ResponseEntity<DossierDto> response = restCaller.call(signicatAssureUrl + "/dossiers", HttpMethod.POST,
        null, DossierDto.class);
    if (response != null && response.getStatusCode() == HttpStatus.CREATED && response.hasBody()) {
      log.info("Create dossier successfully");
      return DossierMapper.toEntity(response.getBody());
    }
    return null;
  }

  @Override
  public Dossier getDossier(String dossierId) {
    if (!StringUtils.hasText(dossierId)) {
      return null;
    }
    log.info("Fetch dossier from Signicat");
    ResponseEntity<DossierDto> response = restCaller.call(signicatAssureUrl + "/dossiers/{dossierId}",
        HttpMethod.GET, null, DossierDto.class, dossierId);
    Dossier dossier = response.getBody() == null ? null : DossierMapper.toEntity(response.getBody());
    log.info("Fetch dossier from Signicat successfully");
    return dossier;
  }

  @Override
  public Process createProcess(String dossierId, ProcessRequest request) {
    if (!StringUtils.hasText(dossierId) || request == null) {
      return null;
    }
    log.info("Request to create process in signicat");
    ProcessRequestDto processBody = ProcessRequestMapper.toDto(request);
    ResponseEntity<ProcessResponseDto> response = restCaller.call(signicatAssureUrl + "/dossiers/{dossierId}/processes",
        HttpMethod.POST, processBody,
        ProcessResponseDto.class, dossierId);
    if (response != null && response.getStatusCode() == HttpStatus.CREATED && response.hasBody()) {
      log.info("Create process successfully");
      return ProcessMapper.toEntity(response.getBody());
    }
    return null;
  }

  @Override
  public Process getProcess(String dossierId, String processId) {
    if (!StringUtils.hasText(dossierId) || !StringUtils.hasText(processId)) {
      return null;
    }
    log.info("Fetch process from Signicat");
    ResponseEntity<ProcessResponseDto> response = restCaller.call(signicatAssureUrl + "/dossiers/{dossierId}/processes/{processId}",
        HttpMethod.GET, null, ProcessResponseDto.class, dossierId, processId);
    Process process = response.getBody() == null ? null : ProcessMapper.toEntity(response.getBody());
    log.info("Fetch process from Signicat successfully");
    return process;
  }

  @Override
  public VerificationResult startVerification(String dossierId, FacetecEnrollment factecEnrollment) {
    if (!StringUtils.hasText(dossierId) || factecEnrollment == null) {
      return null;
    }
    VerifyRequestDto request = VerificationMapper.convert(factecEnrollment);
    log.info("start verification process to signicat");
    ResponseEntity<VerificationResultDto> response = restCaller.call(signicatAssureUrl + "/dossiers/{dossierId}/processes/{processId}",
        HttpMethod.POST, request, VerificationResultDto.class, dossierId, factecEnrollment.getProcessFacetecId());
    VerificationResult result = response.getBody() == null ? null : VerificationMapper.toEntity(response.getBody());
    log.info("finish verifying process to signicat");
    return result;
  }

}
