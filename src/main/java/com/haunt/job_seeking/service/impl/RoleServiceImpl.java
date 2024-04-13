package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.role.RoleRequest;
import com.haunt.job_seeking.dto.response.role.RoleResponse;
import com.haunt.job_seeking.entity.Role;
import com.haunt.job_seeking.exception.role.RoleNameAlreadyExist;
import com.haunt.job_seeking.exception.role.RoleNotFoundException;
import com.haunt.job_seeking.repository.RoleRepository;
import com.haunt.job_seeking.service.RoleService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.haunt.job_seeking.utils.MapperUtils.*;

@Service
@Slf4j
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
  private final RoleRepository repository;

  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public RoleResponse create(RoleRequest request) {
    log.info("(create) request:{}", request);

    checkRoleExistByName(request.getName());
    Role roleCreated = create(toEntity(request, Role.class));

    return toDTO(roleCreated, RoleResponse.class);
  }

  @Override
  public RoleResponse detail(String id) {
    log.info("(detail) id:{}", id);

    return toDTO(findById(id), RoleResponse.class);
  }

  @Override
  public RoleResponse update(String id, RoleRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    Role roleExist = findById(id);
    checkRoleExistByName(request.getName());
    roleExist.setName(request.getName());

    return toDTO(update(roleExist), RoleResponse.class);
  }

  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    Role roleExist = findById(id);
    delete(id);
  }

  @Override
  public List<RoleResponse> list(int size, int page, boolean isAll, String keyword) {
    log.info("(list) size:{} page:{}  isAll:{},  keyword:{}", size, page, isAll, keyword);


    List<Role> roles = isAll ? list() : repository.search(keyword, PageRequest.of(page, size));
    return toDTOs(roles, RoleResponse.class);
  }

  @Override
  public RoleResponse getByName(String name) {
    log.info("(getByName) name:{}", name);

    return toDTO(repository.findByName(name), RoleResponse.class);
  }

  private void checkRoleExistByName(String name) {
    log.info("(checkRoleExistByName) name:{}", name);

    if (repository.existsByName(name)) {
      throw new RoleNameAlreadyExist();
    }
  }

  private Role findById(String id) {
    log.info("(findById) id:{}", id);

    return repository.findById(id).orElseThrow(RoleNotFoundException::new);

  }

}
