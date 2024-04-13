package com.haunt.job_seeking.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileDownloadService {
  MultipartFile downloadImage(String url, String fileName);
}
