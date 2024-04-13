package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
import com.haunt.job_seeking.dto.response.company.CompanyResponse;
import com.haunt.job_seeking.dto.response.image.ImageResponse;
import com.haunt.job_seeking.facade.CompanyFacadeService;
import com.haunt.job_seeking.service.CompanyService;
import com.haunt.job_seeking.service.EmployerService;
import com.haunt.job_seeking.service.ImageService;
import com.haunt.job_seeking.service.MinIOService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyFacadeServiceImpl implements CompanyFacadeService {
  private final CompanyService companyService;
  private final EmployerService employerService;
  private final ImageService imageService;
  private final MinIOService minIOService;

  public CompanyResponse create(CompanyRequest request) {
    log.info("(create) request:{}", request);

    String employerId = request.getEmployerId();

    CompanyResponse companyResponse = companyService.create(request);

    String companyId = companyResponse.getId();

    employerService.updateWithCompany(employerId, companyId);

    this.updateCompanyIdForImage(companyId, request.getImageIds());

    companyResponse.setImages(
          this.setMinIOUrl(request.getImageIds())
    );

    return companyResponse;
  }


  public CompanyResponse detail(String id) {
    log.info("(detail) id:{}", id);

    CompanyResponse companyResponse = companyService.detail(id);

    List<ImageResponse> images = imageService.getImagesByCompany(id);
    List<String> image_keys = new ArrayList<String>();
    for (ImageResponse imageResponse : images) {
      image_keys.add(imageResponse.getKeyImage());
    }
    companyResponse.setImages(image_keys);

    return companyResponse;
  }

  @Override
  public CompanyResponse update(String id, CompanyRequest request) {
    log.info("(update) id:{}, request:{}", id, request);

    String employerId = request.getEmployerId();

    CompanyResponse companyResponse = companyService.update(id, request);

    employerService.updateWithCompany(employerId, id);
    this.updateCompanyIdForImage(id, request.getImageIds());

    companyResponse.setImages(
          this.setMinIOUrl(request.getImageIds())
    );

    return companyResponse;


  }

  public List<CompanyResponse> list(int size, int page, boolean isAll, String keyword) {
    log.info("(list) size:{} page:{} isAll:{} keyword:{}", size, page, isAll, keyword);

    return companyService.list(size, page, isAll, keyword);
  }

  @Override
  public CompanyResponse verify(String id) {
    log.info("(verify) id:{}", id);

    return companyService.verify(id);
  }

  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    companyService.delete(id);

    List<ImageResponse> images = imageService.findByCompanyId(id);
    for (ImageResponse imageResponse : images) {
      imageService.delete(imageResponse.getId());
    }
  }

  private void updateCompanyIdForImage(String companyId, List<String> imageIds) {
    if (Objects.isNull(imageIds) || imageIds.isEmpty()) return;

    imageIds.parallelStream().forEach(
          imageId -> imageService.update(imageId, null, companyId)
    );
  }

  private List<String> setMinIOUrl(List<String> imageIds) {
    if (Objects.isNull(imageIds) || imageIds.isEmpty()) return Collections.emptyList();

    List<String> urls = new ArrayList<>();

    imageIds.parallelStream().forEach(
          imageId -> urls.add(minIOService.getPreSignURL(imageId.concat(".png"), "image/png"))
    );

    return urls;
  }
}
