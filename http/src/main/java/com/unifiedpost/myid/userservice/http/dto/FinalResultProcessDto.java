package com.unifiedpost.myid.userservice.http.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class FinalResultProcessDto {

  private String firstName;
  private String lastName;
  private String gender;
  private String nationality;
  private String dateOfBirth;

}
