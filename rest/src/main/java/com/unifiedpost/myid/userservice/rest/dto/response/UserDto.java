package com.unifiedpost.myid.userservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "User information")
public class UserDto {

  @Schema(description = "The document user’s first name.", example = "John")
  private String firstName;
  @Schema(description = "The document user’s last name.", example = "Reynolds")
  private String lastName;
  @Schema(description = "The user’s gender.", allowableValues = "M, F", example = "F")
  private String gender;
  @Schema(description = "The user’s birthday.", format = "date", example = "1995-05-20", type = "string")
  private String dateOfBirth;
  @Schema(description = "The user's nationality.", example = "PRT")
  private String nationality;
}
