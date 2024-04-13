package com.haunt.job_seeking.dto.response.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor(staticName = "of")
public class AddressPageResponse {
  private List<AddressResponse> addresses;
  private int countAddresses;
}
