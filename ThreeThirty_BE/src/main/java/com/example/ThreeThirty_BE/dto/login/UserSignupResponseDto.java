package com.example.ThreeThirty_BE.dto.login;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupResponseDto {
  private Long user_id;
  private String user_email;
  private String user_name;
  private LocalDate signup_date;
}
