package com.haunt.job_seeking.configuration;

import com.haunt.job_seeking.service.MinIOService;
import com.haunt.job_seeking.service.impl.MinIOServiceImpl;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOClientConfiguration {
  @Value("${application.minio.bucket-name}")
  public String bucketName;

  @Value("${application.minio.url}")
  private String minioUrl;

  @Value("${application.minio.access-key}")
  private String accessKey;

  @Value("${application.minio.secret-key}")
  private String secretKey;

  @Bean
  public MinioClient minioClient() {
    return MinioClient
          .builder()
          .endpoint(minioUrl)
          .credentials(accessKey, secretKey)
          .build();
  }

  @Bean
  public MinIOService minIOService(MinioClient minioClient) {
    return new MinIOServiceImpl(bucketName, minioClient);
  }
}
