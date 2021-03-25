package com.unifiedpost.myid.userservice.db;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserModel implements Serializable {

  @Id @GeneratedValue private Long id;

  private String dossierId;

  private String email;

  private String phoneNumber;


}
