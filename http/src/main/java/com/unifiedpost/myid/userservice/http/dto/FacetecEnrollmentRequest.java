package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacetecEnrollmentRequest {
  private String faceScan;
  private String auditTrailImage;
  private String lowQualityAuditTrailImage;
  private String userAgent;
}
