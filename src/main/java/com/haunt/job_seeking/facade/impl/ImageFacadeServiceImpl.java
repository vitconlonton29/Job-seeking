package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.image.ImageRequest;
import com.haunt.job_seeking.facade.ImageFacadeService;
import com.haunt.job_seeking.service.ImageService;
import com.haunt.job_seeking.service.MinIOService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.haunt.job_seeking.constants.JobSeekingConstants.ContentTypeConstant.*;
import static com.haunt.job_seeking.constants.JobSeekingConstants.CommonConstants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ImageFacadeServiceImpl implements ImageFacadeService {
  private final MinIOService minIOService;
  private final ImageService imageService;

  public List<String> create(List<MultipartFile> images) {
    log.info("(create) image size: {}", images.size());

    List<String> keys = new ArrayList<>();

    for (MultipartFile image : images) {
      String key = UUID.randomUUID().toString();

      ImageRequest imageRequest = ImageRequest.builder()
            .keyImage(key)
            .build();

      imageService.create(imageRequest);

      String filename = key + IMAGE_FORMAT;

      minIOService.putFile(image, APPLICATION_NAME, filename);

      keys.add(key);
    }

    return keys;
  }
}
