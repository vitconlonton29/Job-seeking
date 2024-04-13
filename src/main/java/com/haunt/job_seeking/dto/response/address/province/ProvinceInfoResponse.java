package com.haunt.job_seeking.dto.response.address.province;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProvinceInfoResponse {
  private String provinceName;
  private String provinceNameEn;
  private String provinceCodeName;
  private String code;

  public ProvinceInfoResponse(String provinceName, String provinceNameEn, String provinceCodeName, String code) {
    this.provinceName = provinceName;
    this.provinceNameEn = provinceNameEn;
    this.provinceCodeName = provinceCodeName;
    this.code = code;
  }

  public ProvinceInfoResponse(String provinceName, String provinceNameEn, String provinceCodeName) {
    this.provinceName = provinceName;
    this.provinceNameEn = provinceNameEn;
    this.provinceCodeName = provinceCodeName;
  }
}
