package com.example.ThreeThirty_BE.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {

  private Long id;
  private Long userId;
  private String value;

  public RefreshToken(Long userId, String value) {
    this.userId = userId;
    this.value = value;
  }
}
