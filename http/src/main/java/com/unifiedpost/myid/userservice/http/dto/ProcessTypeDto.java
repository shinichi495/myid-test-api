package com.unifiedpost.myid.userservice.http.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProcessTypeDto {
  DOCUMENT("document"),
  DOCUMENTSELFIE("documentSelfie"),
  DOCUMENTVIDEO("documentVideo"),
  SDK("sdk"),
  READY("ready");

  private String value;

  private ProcessTypeDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static ProcessTypeDto fromString(String value) {
    for (ProcessTypeDto processType : ProcessTypeDto.values()) {
      if (processType.getValue().equalsIgnoreCase(value)) {
        return processType;
      }
    }
    return null;
  }
}
