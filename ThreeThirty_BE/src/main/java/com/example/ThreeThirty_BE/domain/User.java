package com.example.ThreeThirty_BE.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


  private Long user_id;

  private String user_email;

  private String user_name;

  private String pw;

  private String phone_number;

  private LocalDate signup_date;

  private String image_url;

  private boolean notification_status;

  private ProviderType providerType;

  private RoleType roleType;

  private String nick_name;


}
