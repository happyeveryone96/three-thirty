package com.example.ThreeThirty_BE.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {

  private Long token_id;
  private Long user_id;
  private String token_value;

  public RefreshToken(Long user_id, String token_value) {
    this.user_id = user_id;
    this.token_value = token_value;
  }
}
