package com.example.ThreeThirty_BE.service.login;


import com.example.ThreeThirty_BE.domain.RefreshToken;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.RefreshTokenRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtTokenizer jwtTokenizer;

  public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
    refreshTokenRepository.saveRefreshToken(refreshToken);
    return refreshToken;
  }

  public RefreshToken findRefreshToken(Long userId) {
    return refreshTokenRepository.findRefreshToken(userId);
  }

  public void deleteRefreshToken(String authorizationHeader) {

    String token = authorizationHeader.replace("Bearer ", "");
    refreshTokenRepository.deleteRefreshToken(token);
    }

}
