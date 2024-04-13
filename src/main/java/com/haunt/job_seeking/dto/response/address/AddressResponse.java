package com.haunt.job_seeking.dto.response.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.haunt.job_seeking.dto.response.address.district.DistrictInfoResponse;
import com.haunt.job_seeking.dto.response.address.province.ProvinceInfoResponse;
import com.haunt.job_seeking.dto.response.address.ward.WardInfoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
  private String id;
  private WardInfoResponse wards;
  private DistrictInfoResponse districts;
  private ProvinceInfoResponse provinces;
  private String detail;

  public AddressResponse(
        String id, WardInfoResponse wards, DistrictInfoResponse districts, ProvinceInfoResponse provinces, String detail
  ) {
    this.id = id;
    this.wards = wards;
    this.districts = districts;
    this.provinces = provinces;
    this.detail = detail;
  }

  public AddressResponse(
        String addressId,
        String wardName, String wardNameEn, String wardCodeName,
        String districtName, String districtNameEn, String districtCodeName,
        String provinceName, String provinceNameEn, String provinceCodeName,
        String detail
  ) {
    this.id = addressId;
    this.wards = new WardInfoResponse(
          wardName,
          wardNameEn,
          wardCodeName
    );
    this.districts = new DistrictInfoResponse(
          districtName,
          districtNameEn,
          districtCodeName
    );
    this.provinces = new ProvinceInfoResponse(
          provinceName,
          provinceNameEn,
          provinceCodeName
    );
    this.detail = detail;
  }

}

