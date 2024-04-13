package com.haunt.job_seeking.facade;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageFacadeService {
  List<String> create(List<MultipartFile> images);
}
