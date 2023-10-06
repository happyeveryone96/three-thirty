package com.example.ThreeThirty_BE.service.login;


import com.example.ThreeThirty_BE.domain.ProviderType;
import com.example.ThreeThirty_BE.domain.RoleType;
import com.example.ThreeThirty_BE.domain.User;
import com.example.ThreeThirty_BE.mapper.UserRepository;
import com.example.ThreeThirty_BE.security.auth.exception.OAuthProviderMissMatchException;
import com.example.ThreeThirty_BE.security.auth.userInfo.OAuth2UserInfo;
import com.example.ThreeThirty_BE.security.auth.userInfo.OAuth2UserInfoFactory;
import com.example.ThreeThirty_BE.security.auth.userInfo.UserPrincipal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
//OAuth2를 사용하여 사용자를 로드하는 서비스 클래스입니다. Google, Kakao, Naver 등 다양한 OAuth2 공급자에 대한 사용자 정보를 가져와 데이터베이스에서 사용자를 로드 또는 생성합니다.
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    try {
      return this.process(userRequest, oAuth2User);
    } catch (Exception ex) {
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
      // 시스템 문제로 내부 인증관련 처리 요청을 할 수 없는 경우
    }
  }

  private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

    // GOOGLE, KAKAO, NAVER, FACEBOOK 등 provider 타입을 가져온다
    ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration()
        .getRegistrationId().toUpperCase());
    System.out.println(providerType);
    // providerType, Attributes로 분류하여 user 정보를 가져온다
    OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType,
        oAuth2User.getAttributes());
    System.out.println(userInfo.getEmail());
    System.out.println(userInfo.getName());
    System.out.println(userInfo.getAttributes());

    User user = userRepository.findByEmail(userInfo.getEmail());

    // 이미 가입되어 있고 ProviderType 이 다르면 업데이트
    if (user != null) {
      if (providerType != user.getProviderType()) {
        throw new OAuthProviderMissMatchException(
                  "you're signed up with " + providerType + " account. Please use your " + user.getProviderType() + " account to login.");
      }
      user = updateMember(user, userInfo);
    } else { // 가입되어있지 않은 회원이면 가입

        user = createUser(userInfo, providerType);
    }

    // OAuth2User 상속받은 MemberPrincipal 객체에 담아서 반환
    return new UserPrincipal(
        user.getUser_id(),
        user.getUser_email(),
        user.getUser_name(),
        user.getProviderType(),
        Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode()))
    );
  }

  // 받은 데이터로 DB 저장
  LocalDate localDate = LocalDate.now();
  private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
    User user = User.builder()
        .user_email(userInfo.getEmail())
        .user_name(userInfo.getName())
        .pw(passwordEncoder.encode(providerType.name() + userInfo.getEmail()))
        .signup_date(localDate)
        .providerType(providerType)
        .roleType(RoleType.USER)
        .build();
    userRepository.saveUser(user);
    return userRepository.findByEmail(user.getUser_email());
  }

  private User updateMember(User user, OAuth2UserInfo userInfo) {
    if (userInfo.getName() != null && !user.getUser_name().equals(userInfo.getName())) {
      userRepository.updateUser(user.getUser_id(), userInfo.getName());
      user = userRepository.findByEmail(user.getUser_email());
    }
    return user;
  }
}
