package com.unifiedpost.myid.userservice.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Liveness information object. Used only when using the Facetec provider")
public class FacetecEnrollmentRequestDto {

  @Schema(description = "The process Id of facetec process used for verification")
  @NotNull
  private String processFacetecId;
  @Schema(description = "The 3D FaceScan is an encrypted byte blob that contains reverse engineered 3D data from 100+ video frames captured during ~2 second User Selfie. FaceScans are always encrypted and are not human viewable",
      example = "")
  @NotNull
  private String faceScan;
  @Schema(description = "Picture taken for the end-user to audit")
  @NotNull
  private String auditTrailImage;
  @Schema(description = "Low quality picture taken to the end user for audit")
  @NotNull
  private String lowQualityAuditTrailImage;
  @NotNull
  private String userAgent;
}
