package com.unifiedpost.myid.userservice.entities;

public class OnfidoProcess extends Process {

  public OnfidoProcess() {
    super.setProvider(Provider.ONFIDO);
  }

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
