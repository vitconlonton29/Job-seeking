package com.haunt.job_seeking.dto.response.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
  private String id;
  private String keyImage;
  private String accountId;
  private String companyId;
  private Boolean isDeleted;
}
