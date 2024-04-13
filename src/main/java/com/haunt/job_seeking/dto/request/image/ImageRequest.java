package com.haunt.job_seeking.dto.request.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequest {
  private String keyImage;
  private String accountId;
  private String companyId;
}
