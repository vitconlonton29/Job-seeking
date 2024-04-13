package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.facade.ImageFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
  private final ImageFacadeService imageFacadeService;

  @PostMapping
  public ResponseGeneral<List<String>> create(
        @RequestParam("images") List<MultipartFile> images
  ) {
    log.info("(create) images size: {}", images);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          imageFacadeService.create(images)
    );

  }
}
