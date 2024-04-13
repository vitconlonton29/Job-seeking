package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.image.ImageRequest;
import com.haunt.job_seeking.dto.response.image.ImageResponse;
import com.haunt.job_seeking.entity.Image;
import com.haunt.job_seeking.repository.ImageRepository;
import com.haunt.job_seeking.service.ImageService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.haunt.job_seeking.utils.MapperUtils.toDTO;
import static com.haunt.job_seeking.utils.MapperUtils.toDTOs;

@Service
@Slf4j
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService {
  private final ImageRepository repository;

  public ImageServiceImpl(ImageRepository repository) {
    super(repository);
    this.repository = repository;
  }


  public ImageResponse create(ImageRequest request) {
    log.info("(create) request:{}", request);

    Image image = Image.from(request);

    return toDTO(create(image), ImageResponse.class);
  }

  @Override
  public void update(String id, String accountId, String companyId) {
    log.info("(update) id:{}, accountId:{}, companyId:{}", id, accountId, companyId);

    Image image = findById(id);

    image.setAccountId(accountId);
    image.setCompanyId(companyId);

    this.update(image);
  }

  @Override
  public List<ImageResponse> findByCompanyId(String companyId) {
    log.info("(findByCompanyId) companyId:{}", companyId);

    return toDTOs(repository.findByCompanyId(companyId), ImageResponse.class);
  }

  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    Image image = get(id);
    image.setIsDeleted(true);

    repository.save(image);
  }

  public List<ImageResponse> getImagesByCompany(String companyId) {
    log.info("(getImagesByCompany) companyId:{}", companyId);

    return toDTOs(repository.findByCompanyId(companyId), ImageResponse.class);
  }

  private Image findById(String id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
  }
}
