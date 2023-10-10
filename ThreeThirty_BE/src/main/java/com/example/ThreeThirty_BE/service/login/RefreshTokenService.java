package com.example.ThreeThirty_BE.service.login;


import com.example.ThreeThirty_BE.domain.RefreshToken;
import com.example.ThreeThirty_BE.mapper.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
    refreshTokenRepository.saveRefreshToken(refreshToken);
    return refreshToken;
  }

  public RefreshToken findRefreshToken(Long userId) {
    return refreshTokenRepository.findRefreshToken(userId);
  }

  public void deleteRefreshToken(String token) {
    System.out.println(token);
    refreshTokenRepository.deleteRefreshToken(token);
  }
}
