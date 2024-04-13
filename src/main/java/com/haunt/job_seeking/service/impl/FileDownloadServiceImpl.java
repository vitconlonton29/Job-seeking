package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.service.FileDownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.haunt.job_seeking.constants.JobSeekingConstants.ContentTypeConstant.OCTET_STREAM;
import static com.haunt.job_seeking.constants.JobSeekingConstants.MethodRequestConstant.GET_METHOD;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService {
  private static HttpURLConnection establishHttpConnection(String downloadURL) throws IOException, URISyntaxException {
    URI uri = new URI(downloadURL);
    URL url = uri.toURL();

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod(GET_METHOD);
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);

    int responseCode = connection.getResponseCode();
    log.warn("(establishHttpConnection) responseCode: {}", responseCode);

    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new IOException("Failed to download file: HTTP error code " + responseCode);
    }

    return connection;
  }

  @Override
  public MultipartFile downloadImage(String urlStr, String fileName) {
    log.debug("(downloadImage) urlStr: {}, fileName: {}", urlStr, fileName);

    int retries = 0;

    while (retries < 3) {
      try {
        return this.downloadFileFromURL(urlStr, fileName, OCTET_STREAM);
      } catch (Exception e) {
        log.error("(downloadImage) Exception: {}", e.getMessage());
      }

      retries++;
    }
    return null;
  }

  private MultipartFile downloadFileFromURL(String downloadURL, String fileName, String contentType) throws IOException, URISyntaxException {
    log.info("(downloadFileFromURL) downloadURL: {}, fileName: {}, contentType: {}", downloadURL, fileName, contentType);

    InputStream inputStream = establishHttpConnection(downloadURL).getInputStream();

    return new MockMultipartFile(
          fileName,
          fileName,
          contentType,
          inputStream
    );
  }
}
