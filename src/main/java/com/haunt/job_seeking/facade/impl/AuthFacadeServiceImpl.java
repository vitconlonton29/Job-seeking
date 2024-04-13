package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.account.AccountRequest;
import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import com.haunt.job_seeking.dto.request.employer.EmployerRequest;
import com.haunt.job_seeking.dto.request.user.UserRequest;
import com.haunt.job_seeking.dto.response.auth.LoginResponse;
import com.haunt.job_seeking.dto.response.auth.RegistrationResponse;
import com.haunt.job_seeking.facade.AuthFacadeService;
import com.haunt.job_seeking.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.AuthConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthFacadeServiceImpl implements AuthFacadeService {
  private final AccountService accountService;
  private final UserService userService;
  private final JwtService jwtService;
  private final RoleService roleService;
  private final EmployerService employerService;

  @Override
  public LoginResponse login(String username, String password) {
    log.info("(login) username:{} password:{}", username, password);

    var account = accountService.findByUsername(username);
    accountService.equalPassword(password, account.getPassword());

    List<GrantedAuthority> authorities = getAuthorities(account.getRoleId());
    var claims = new HashMap<String, Object>();
    claims.put(CLAIM_USERNAME_KEY, username);
    claims.put(CLAIM_AUTHORITIES_KEY, authorities);

    String accessToken = jwtService.generateAccessToken(account.getId(), claims);
    String refreshToken = jwtService.generateRefreshToken(account.getId(), account.getUsername());

    return new LoginResponse(accessToken, refreshToken);

  }


  @Override
  public RegistrationResponse register(RegistrationRequest request) {
    log.info("(register) request: {}", request);

    String roleId = getRoleIdBasedOnType(request.getType());

    AccountRequest accountRequest = new AccountRequest(request.getUsername(), request.getPassword(), roleId);
    String accountId = accountService.create(accountRequest).getId();

    userService.create(UserRequest.from(request), accountId);

    if (EMPLOYER.equals(request.getType())) {
      employerService.create(EmployerRequest.from(request), accountId);
    }

    return buildRegistrationResponse(request, accountId, roleId);
  }

  private String getRoleIdBasedOnType(String userType) {
    return EMPLOYER.equals(userType) ? roleService.getByName(EMPLOYER).getId() : roleService.getByName(USER).getId();
  }

  private RegistrationResponse buildRegistrationResponse(RegistrationRequest request, String accountId, String roleId) {
    return new RegistrationResponse(
          accountId,
          request.getFullName(),
          request.getSex(),
          request.getPhoneNumber(),
          request.getEmail(),
          request.getAddress(),
          request.getDob(),
          request.getUsername(),
          roleId
    );
  }


  private List<GrantedAuthority> getAuthorities(String roleId) {
    log.info("(getAuthorities)roleId: {}", roleId);

    if (roleId != null) {
      return Collections.singletonList(() -> "ROLE_" + roleId);
    }
    return Collections.emptyList();
  }


  public String getCurrentUser() {
    log.info("(getCurrentUser) start");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      return authentication.getName();
    } else {
      return null;
    }


  }
}
