package com.unifiedpost.myid.userservice.entities;

public class FacetecEnrollment {

  private String processFacetecId;
  private String faceScan;
  private String auditTrailImage;
  private String lowQualityAuditTrailImage;
  private String userAgent;

  public String getProcessFacetecId() {
    return processFacetecId;
  }

  public void setProcessFacetecId(String processFacetecId) {
    this.processFacetecId = processFacetecId;
  }

  public String getFaceScan() {
    return faceScan;
  }

  public void setFaceScan(String faceScan) {
    this.faceScan = faceScan;
  }

  public String getAuditTrailImage() {
    return auditTrailImage;
  }

  public void setAuditTrailImage(String auditTrailImage) {
    this.auditTrailImage = auditTrailImage;
  }

  public String getLowQualityAuditTrailImage() {
    return lowQualityAuditTrailImage;
  }

  public void setLowQualityAuditTrailImage(String lowQualityAuditTrailImage) {
    this.lowQualityAuditTrailImage = lowQualityAuditTrailImage;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
