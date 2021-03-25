package com.unifiedpost.myid.userservice.entities;

public enum ProcessType {

  DOCUMENT("document"), DOCUMENT_SELFIE("documentSelfie"), DOCUMENT_VIDEO("documentVideo"),
  SDK("sdk"), READY("ready");

  private String value;

  ProcessType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static ProcessType fromString(String value) {
    for (ProcessType processType : ProcessType.values()) {
      if (processType.getValue().equalsIgnoreCase(value)) {
        return processType;
      }
    }
    return null;
  }
}
