package com.haunt.job_seeking.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JwtService {
  String generateAccessToken(String userId, Map<String, Object> claims);

  String generateRefreshToken(String userId, String username);

  String getSubjectFromToken(String token);

  Long getExpirationTime(String token);

  String getUsernameFromToken(String token);
}
