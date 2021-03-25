package com.unifiedpost.myid.userservice.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseExceptionDto implements Serializable {

  @JsonIgnore
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String instance;
  @JsonIgnore
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String method;
  @JsonIgnore
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String type;
  private LocalDateTime timestamp = LocalDateTime.now();
  private int status;
  private String title;
  private String detail;
  private String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
}
