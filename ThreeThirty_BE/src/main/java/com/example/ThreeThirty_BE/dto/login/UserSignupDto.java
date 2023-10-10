package com.example.ThreeThirty_BE.dto.login;

import lombok.Getter;

@Getter
public class UserSignupDto {

  private String user_email;
  private String user_name;
  private String pw;
  private String phone_number;
  private String image_url;
  private boolean notification_status;
  private String nick_name;
}
