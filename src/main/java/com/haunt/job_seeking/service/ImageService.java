package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.image.ImageRequest;
import com.haunt.job_seeking.dto.response.image.ImageResponse;
import com.haunt.job_seeking.entity.Image;
import com.haunt.job_seeking.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService extends BaseService<Image> {
  ImageResponse create(ImageRequest request);

  void update(String id, String accountId, String companyId);

  List<ImageResponse> findByCompanyId(String companyId);

  void delete(String id);

  List<ImageResponse> getImagesByCompany(String companyId);
}
