package com.unifiedpost.myid.userservice.entities;

public class FacetecProcess extends Process {

  public FacetecProcess() {
    super.setProvider(Provider.FACETEC);
  }

  private FacetecMatching matching;
  private String facetecEncryptionPublicKey;
  private String facetecDeviceKeyIdentifier;

  public String getFacetecEncryptionPublicKey() {
    return facetecEncryptionPublicKey;
  }

  public void setFacetecEncryptionPublicKey(String facetecEncryptionPublicKey) {
    this.facetecEncryptionPublicKey = facetecEncryptionPublicKey;
  }

  public String getFacetecDeviceKeyIdentifier() {
    return facetecDeviceKeyIdentifier;
  }

  public void setFacetecDeviceKeyIdentifier(String facetecDeviceKeyIdentifier) {
    this.facetecDeviceKeyIdentifier = facetecDeviceKeyIdentifier;
  }

  public FacetecMatching getMatching() {
    return matching;
  }

  public void setMatching(FacetecMatching matching) {
    this.matching = matching;
  }
}
