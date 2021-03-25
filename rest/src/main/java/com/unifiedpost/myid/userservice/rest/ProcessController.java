package com.unifiedpost.myid.userservice.rest;

import com.unifiedpost.myid.userservice.entities.FacetecEnrollment;
import com.unifiedpost.myid.userservice.entities.Process;
import com.unifiedpost.myid.userservice.entities.ProcessRequest;
import com.unifiedpost.myid.userservice.rest.dto.request.FacetecEnrollmentRequestDto;
import com.unifiedpost.myid.userservice.rest.dto.request.ProcessRequestDto;
import com.unifiedpost.myid.userservice.rest.dto.response.*;
import com.unifiedpost.myid.userservice.rest.mapper.FactecEnrollmentMapper;
import com.unifiedpost.myid.userservice.rest.mapper.ProcessMapper;
import com.unifiedpost.myid.userservice.rest.mapper.ProcessRequestMapper;
import com.unifiedpost.myid.userservice.usecases.CreateProcessUseCase;
import com.unifiedpost.myid.userservice.usecases.GetProcessUseCase;
import com.unifiedpost.myid.userservice.usecases.StartVerificationUseCase;
import com.unifiedpost.myid.userservice.usecases.adapter.UserAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

@Tag(name = "Processes", description = "A process is an object that contains end-user identity data as well as identity verification result data obtained from the provider that is used to verify the information in the process")
@RestController
public class ProcessController {

  @Autowired
  private GetProcessUseCase getProcessUseCase;

  @Autowired
  private CreateProcessUseCase createProcessUseCase;

  @Autowired
  private StartVerificationUseCase startVerificationUseCase;

  @Autowired
  private UserAdapter userAdapter;

  @Operation(summary = "Create Process",
      description = "Initializes a new identity process in the dossier, using a given provider.",
      parameters = {@Parameter(in = ParameterIn.PATH, name = "dossierId", description = "The ID of the dossier where the process should be created. This is obtained from the \"IdProofing\" response body.", example = "49296926-7b41-4a74-9cf6-50d65f675b5f")}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The process has been successfully initialized in the given dossier.",
          content = @Content(schema = @Schema(
              oneOf = {ProcessInitializationDto.class, FacetecInitializationDto.class}))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "404", description = "Dossier not found.", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(hidden = true)))})
  @PostMapping("/dossiers/{dossierId}/processes")
  public ResponseEntity<ProcessInitializationDto> createProcess(@PathVariable String dossierId, @Valid @RequestBody ProcessRequestDto requestDto) {
    ProcessRequest request = ProcessRequestMapper.toEntity(requestDto);
    Process process = createProcessUseCase.execute(dossierId, request);
    if (process == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Cannot create process in dossier with given provider");
    }
    return new ResponseEntity<>(ProcessMapper.toInitializationDto(process), HttpStatus.OK);
  }

  @Operation(summary = "Get Process", description = "Gets all the information about a process, such as the process status or information about the verification result.",
      parameters = {@Parameter(in = ParameterIn.PATH, name = "dossierId", description = "The id of a dossier. This is obtained from the \"IdProofing\" response body.", example = "49296926-7b41-4a74-9cf6-50d65f675b5f"),
          @Parameter(in = ParameterIn.PATH, name = "processId", description = "The id of a process. This is obtained from the \"Create Process\" response body.", example = "339f82c8-4ca6-4331-9ec2-cc3a61885285")})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The process information was successfully retrieved.", content = @Content(schema = @Schema(oneOf = {OnfidoDetailDto.class, ReadIdDetailDto.class, FacetecDetailDto.class}))),
      @ApiResponse(responseCode = "404", description = "Dossier not found. | Process ID: doesn't exist", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "500", description = "Fail to get process", content = @Content(schema = @Schema(hidden = true)))})
  @GetMapping(value = "/dossiers/{dossierId}/processes/{processId}")
  public ResponseEntity<ProcessDetailDto> getProcess(@PathVariable String dossierId, @PathVariable String processId) {
    Process process = getProcessUseCase.execute(dossierId, processId);
    if (process == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Process ID: " + processId + " doesn't exist");
    }
    return new ResponseEntity<>(ProcessMapper.toDetailDto(process), HttpStatus.OK);
  }

  @Operation(summary = "Start Verification",
      description = "This service requests is to perform a verification of the identity document that was uploaded, then return process after handling",
      parameters = {@Parameter(in = ParameterIn.PATH, name = "dossierId", description = "This is obtained from the \"IdProofing\" response body.", example = "49296926-7b41-4a74-9cf6-50d65f675b5f"),
          @Parameter(in = ParameterIn.PATH, name = "processId", description = "This is obtained from the \"IdProofing\" response body.", example = "339f82c8-4ca6-4331-9ec2-cc3a61885285")})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The process with information has been successfully verified successfully.",
          content = @Content(schema = @Schema(oneOf = {OnfidoDetailDto.class, ReadIdDetailDto.class}))),
      @ApiResponse(responseCode = "404", description = "The dossier was not found. | The process was not found.", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "400", description = "Verification already started.", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "417", description = "Verification failed. Data does not match.", content = @Content(schema = @Schema(hidden = true)))
  })
  @PostMapping("/dossiers/{dossierId}/processes/{processId}")
  public ResponseEntity<ProcessDetailDto> startVerification(@PathVariable String dossierId, @PathVariable String processId, @Valid @RequestBody FacetecEnrollmentRequestDto requestDto) {
    FacetecEnrollment request = FactecEnrollmentMapper.toEntity(requestDto);
    startVerificationUseCase.execute(dossierId, request);
    Process facetecProcess = getProcessUseCase.execute(dossierId, request.getProcessFacetecId());
    if ("accepted".equalsIgnoreCase(facetecProcess.getStatus())) {
      return getProcess(dossierId, processId);
    } else {
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Verification failed. Data does not match");
    }
  }

}
