package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.user.UserRequest;
import com.haunt.job_seeking.dto.response.user.UserResponse;
import com.haunt.job_seeking.entity.User;
import com.haunt.job_seeking.exception.user.EmailAlreadyExistException;
import com.haunt.job_seeking.exception.user.PhoneNumberAlreadyExistException;
import com.haunt.job_seeking.repository.UserRepository;
import com.haunt.job_seeking.service.UserService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.haunt.job_seeking.utils.MapperUtils.*;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

  private UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public UserResponse create(UserRequest request, String id) {
    log.info("(create) request:{} id:{}", request, id);

    checkExistedUser(request.getEmail(), request.getPhoneNumber());

    User user = toEntity(request, User.class);
    user.setId(id);

    return toDTO(create(user), UserResponse.class);
  }

  private void checkExistedUser(String email, String phoneNumber) {
    log.info("(checkExistedUser) email:{} phoneNumber:{}", email, phoneNumber);

    checkExistedEmail(email);
    checkExistedPhoneNumber(phoneNumber);

  }

  private void checkExistedEmail(String email) {
    log.info("(checkExistedEmail) email:{}", email);

    if (repository.existsByEmail(email))
      throw new EmailAlreadyExistException();
  }

  private void checkExistedPhoneNumber(String phoneNumber) {
    log.info("(checkExistedPhoneNumber) phoneNumber:{}", phoneNumber);

    if (repository.existsByPhoneNumber(phoneNumber))
      throw new PhoneNumberAlreadyExistException();
  }
}
