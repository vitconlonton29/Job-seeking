package com.haunt.job_seeking.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
  String getPreSignURL(String filename, String contentType);

  void putFile(MultipartFile file, String bucketName, String filename);

  void putFile(String filePath, String bucketName, String fileName);

  byte[] getFile(String filename, String bucketName);

  boolean remove(String filename, String bucketName);
}
