package com.haunt.job_seeking.dto.response.address.ward;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class WardInfoResponse {
  private String wardName;
  private String wardNameEn;
  private String wardCodeName;
  private String code;

  public WardInfoResponse(String wardName, String wardNameEn, String wardCodeName) {
    this.wardName = wardName;
    this.wardNameEn = wardNameEn;
    this.wardCodeName = wardCodeName;
  }

  public WardInfoResponse(String wardName, String wardNameEn, String wardCodeName, String code) {
    this.wardName = wardName;
    this.wardNameEn = wardNameEn;
    this.wardCodeName = wardCodeName;
    this.code = code;
  }
}
