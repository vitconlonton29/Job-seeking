package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.configuration.MinIOClientConfiguration;
import com.haunt.job_seeking.service.FileDownloadService;
import com.haunt.job_seeking.service.MinIOService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class TestMinIOCController {
  private final MinIOService minIOService;
  private final FileDownloadService fileDownloadService;
  private final MinIOClientConfiguration minIOClientConfiguration;

  @PostMapping("test")
  public void test() {
    MultipartFile file = fileDownloadService.downloadImage(
          "https://khoinguonsangtao.vn/wp-content/uploads/2022/08/hinh-anh-meo-cute-1-420x560.jpg",
          "anh-meo"
    );

    minIOService.putFile(file, minIOClientConfiguration.bucketName, "anh-meo".concat(".png"));
  }
}
