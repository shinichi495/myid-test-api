package com.unifiedpost.myid.userservice.entities;

public class VerificationResult {
  private String processId;
  private String status;

  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
