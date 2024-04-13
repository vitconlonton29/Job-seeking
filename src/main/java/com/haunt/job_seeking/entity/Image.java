package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.image.ImageRequest;
import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="images")
public class Image extends BaseEntityWithUpdater {
  private String keyImage;
  private String accountId;
  private String companyId;
  private Boolean isDeleted;

  public static Image from(ImageRequest request) {
    Image image = new Image();
    image.keyImage = request.getKeyImage();
    image.accountId = request.getAccountId();
    image.companyId = request.getCompanyId();
    image.isDeleted = false;
    return image;
  }

}
