package com.example.ThreeThirty_BE.security.auth.userInfo;

import java.util.Map;
// Google OAuth2로부터 사용자 정보 추출하는 클래스, OAuth2UserInfo 클래스를 상속하고 ID,이름,이메일을 추출하여 반환
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

  public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  public String getId() {
    return (String) attributes.get("sub");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }
}
