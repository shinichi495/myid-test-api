package com.unifiedpost.myid.userservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProcessTypeDto {

  DOCUMENT("document"), DOCUMENT_SELFIE("documentSelfie"), DOCUMENT_VIDEO("documentVideo"),
  SDK("sdk"), READY("ready");

  private String value;

  ProcessTypeDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
