package com.unifiedpost.myid.userservice.entities;

public class ProcessRequest {

  private Provider provider;
  private ProcessType processType;
  private ProcessParameter processParameter;

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public ProcessType getProcessType() {
    return processType;
  }

  public void setProcessType(ProcessType processType) {
    this.processType = processType;
  }

  public ProcessParameter getProcessParameter() {
    return processParameter;
  }

  public void setProcessParameter(ProcessParameter processParameter) {
    this.processParameter = processParameter;
  }

}
