package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.exception.base.InternalServerError;
import com.haunt.job_seeking.service.MinIOService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class MinIOServiceImpl implements MinIOService {
  private final String bucketName;
  private final MinioClient minioClient;

  @Override
  public String getPreSignURL(String filename, String contentType) {
    log.info("(getPreSignURL) time: {} ===== START", Instant.now().getEpochSecond());
    Map<String, String> reqParams = new HashMap<>();
    reqParams.put("response-content-type", contentType);

    try {
      minioClient.statObject(
            StatObjectArgs.builder()
                  .bucket(bucketName)
                  .object(filename)
                  .build()
      );
      return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                  .method(Method.GET)
                  .bucket(bucketName)
                  .object(filename)
                  .extraQueryParams(reqParams)
                  .expiry(2, TimeUnit.DAYS)
                  .build()
      );
    } catch (ErrorResponseException e) {
      log.error("(getPreSignURL) ErrorResponseException: {}", e.errorResponse());
      return null;
    } catch (InsufficientDataException | InternalException | InvalidKeyException |
             InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException |
             ServerException e
    ) {
      log.error("(getPreSignURL) Exception: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    } finally {
      log.info("(getPreSignURL) time: {} ===== END", Instant.now().getEpochSecond());
    }

  }

  @Async
  @Override
  public void putFile(MultipartFile file, String bucket, String filename) {
    log.info("(putFile) filename: {}", filename);
    try {
      InputStream inputStream = file.getInputStream();
      minioClient.putObject(PutObjectArgs
            .builder()
            .bucket(bucketName)
            .object(filename)
            .stream(inputStream, file.getSize(), -1)
            .contentType(file.getContentType()).build()
      );

      log.info("(putFile) filename: {} success", filename);
    } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
             InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
             XmlParserException e) {
      log.error("(putFile) Exception: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }

  @Async
  @Override
  public void putFile(String filePath, String bucketName, String fileName) {
    log.info("(putFile) filename: {}", fileName);

    Path path = Paths.get(filePath);
    String contentType;

    try (InputStream inputStream = new FileInputStream(filePath)) {
      contentType = Files.probeContentType(path);
      long fileSize = Files.size(path);

      minioClient.putObject(
            PutObjectArgs.builder()
                  .bucket(bucketName)
                  .object(fileName)
                  .stream(inputStream, fileSize, -1)
                  .contentType(contentType)
                  .build()
      );
    } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
             InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
             XmlParserException e) {
      log.error("(putFile) Exception: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    } finally {
      try {
        log.info("(putFile) delete {} in local", fileName);

        Files.deleteIfExists(path);
      } catch (IOException e) {
        log.error("(putFile) Exception: {}", e.getMessage());
      }
    }

  }

  @Override
  public byte[] getFile(String filename, String bucketName) {
    try {
      InputStream stream = minioClient.getObject(GetObjectArgs
            .builder()
            .bucket(bucketName)
            .object(filename)
            .build()
      );
      return stream.readAllBytes();
    } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
             InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
             XmlParserException e) {
      log.error("(getFile) Exception: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public boolean remove(String filename, String bucketName) {
    try {
      minioClient.removeObject(RemoveObjectArgs
            .builder()
            .bucket(bucketName)
            .object(filename)
            .build()
      );
    } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
             InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
             XmlParserException e) {
      log.error("(remove) Exception: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    }

    return true;
  }
}
