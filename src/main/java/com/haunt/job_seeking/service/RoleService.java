package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.role.RoleRequest;
import com.haunt.job_seeking.dto.response.role.RoleResponse;
import com.haunt.job_seeking.entity.Role;
import com.haunt.job_seeking.service.base.BaseService;

import java.util.List;

public interface RoleService extends BaseService<Role> {
  RoleResponse create(RoleRequest request);

  RoleResponse detail(String id);

  RoleResponse update(String id, RoleRequest request);

  void delete(String id);

  List<RoleResponse> list(int size, int page, boolean isAll, String keyword);

  RoleResponse getByName(String name);
}
