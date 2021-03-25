package com.unifiedpost.myid.userservice.rest;

import com.unifiedpost.myid.userservice.entities.Dossier;
import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.ProcessRequest;
import com.unifiedpost.myid.userservice.entities.User;
import com.unifiedpost.myid.userservice.rest.dto.request.IdProofingRequestDto;
import com.unifiedpost.myid.userservice.rest.dto.response.IdProofingResponseDto;
import com.unifiedpost.myid.userservice.rest.mapper.IdProofingResponseMapper;
import com.unifiedpost.myid.userservice.rest.mapper.ProcessRequestMapper;
import com.unifiedpost.myid.userservice.usecases.CreateDossierUseCase;
import com.unifiedpost.myid.userservice.usecases.CreateProcessUseCase;
import com.unifiedpost.myid.userservice.usecases.adapter.UserAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Tag(name = "IdProofing", description = "A Dossier goes with processes are created to hold end-user identify data")
@RestController
public class IdProofingsController {

  @Autowired
  private CreateDossierUseCase createDossierUseCase;
  @Autowired
  private CreateProcessUseCase createProcessUseCase;
  @Autowired
  private UserAdapter userAdapter;

  @Operation(summary = "Create IdProofing",
      description = "Create a new dossier for each user to perform identity verification for that user and create a new process"
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Create Id proofing successfully.",
          content = @Content(
              schema = @Schema(implementation = IdProofingResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "500", description = "Couldn't create Id proofing",
          content = @Content(schema = @Schema(hidden = true)))
  })
  @PostMapping(value = "/idproofings")
  public ResponseEntity<?> createIdProofing(@Valid @RequestBody IdProofingRequestDto idProofingRequestDto) {
    ProcessRequest processRequest = ProcessRequestMapper.convert(idProofingRequestDto);
    User user = userAdapter.findByEmail(idProofingRequestDto.getEmail());
    Dossier dossier = createDossierUseCase.execute(user);
    if (dossier == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't create Id proofing");
    }
    Process process = createProcessUseCase.execute(dossier.getDossierId(), processRequest);
    if (process == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't create Id proofing");
    }
    if (user == null) {
      user = new User();
      user.setDossierId(dossier.getDossierId());
      user.setPhoneNumber(idProofingRequestDto.getPhoneNumber());
      user.setEmail(idProofingRequestDto.getEmail());
      userAdapter.save(user);
    }
    return new ResponseEntity<>(IdProofingResponseMapper.toDto(dossier, process), HttpStatus.CREATED);
  }
}
