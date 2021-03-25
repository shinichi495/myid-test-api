package com.unifiedpost.myid.userservice.entities;

public enum Provider {
  ONFIDO("onfido"), READID("readid"), FACETEC("facetec");

  private String value;

  Provider(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Provider fromString(String value) {
    for (Provider provider : Provider.values()) {
      if (provider.value.equalsIgnoreCase(value)) {
        return provider;
      }
    }
    return null;
  }
}
