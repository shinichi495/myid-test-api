package com.unifiedpost.myid.userservice.entities;

public class ReadIdProcess extends Process {

  public ReadIdProcess() {
    super.setProvider(Provider.READID);
  }

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
