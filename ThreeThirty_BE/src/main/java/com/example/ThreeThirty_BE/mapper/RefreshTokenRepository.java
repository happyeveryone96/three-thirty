package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.RefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenRepository {

  void saveRefreshToken(RefreshToken refreshToken);

  RefreshToken findRefreshToken(Long userId);

  void updateRefreshToken(@Param("user_id") Long userId, @Param("token_value") String value);

  void deleteRefreshToken(String refreshToken);
}
