package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.user.UserRequest;
import com.haunt.job_seeking.dto.response.user.UserResponse;
import com.haunt.job_seeking.entity.User;
import com.haunt.job_seeking.service.base.BaseService;

public interface UserService extends BaseService<User> {
  UserResponse create(UserRequest request, String id);
}
