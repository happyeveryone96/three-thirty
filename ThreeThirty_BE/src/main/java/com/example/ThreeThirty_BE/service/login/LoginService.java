package com.example.ThreeThirty_BE.service.login;


import com.example.ThreeThirty_BE.domain.ProviderType;
import com.example.ThreeThirty_BE.domain.RefreshToken;
import com.example.ThreeThirty_BE.domain.RoleType;
import com.example.ThreeThirty_BE.domain.User;
import com.example.ThreeThirty_BE.dto.login.UserAddDataDto;
import com.example.ThreeThirty_BE.dto.login.UserLoginDto;
import com.example.ThreeThirty_BE.dto.login.UserLoginResponseDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupResponseDto;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.UserRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import com.example.ThreeThirty_BE.service.user.UserService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenizer jwtTokenizer;
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;

  @Transactional
  public UserSignupResponseDto saveUser(UserSignupDto userSignupDto) {
    if (userSignupDto.getUser_email() == null || userSignupDto.getUser_name() == null ||
        userSignupDto.getPw() == null || userSignupDto.getPhone_number() == null ||
        userSignupDto.getNick_name() == null) {
      throw new CustomException(ErrorCode.MISSING_REQUIRED_FIELDS);
    }

    // 해당 이메일로 가입된 정보가 이미 있을 경우
    if (userRepository.findByEmail(userSignupDto.getUser_email()) != null) {
      throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
    }
    /// 해당 전화번호로 이미 가입된 정보가 이미 있을 경우
    if (userRepository.findByPhoneNum(userSignupDto.getPhone_number())) {
      throw new CustomException(ErrorCode.PHONE_NUMBER_DUPLICATE);
    }

    LocalDate localDate = LocalDate.now();

    User user = User.builder()
        .user_email(userSignupDto.getUser_email())
        .user_name(userSignupDto.getUser_name())
        .pw(passwordEncoder.encode(userSignupDto.getPw()))
        .phone_number(userSignupDto.getPhone_number())
        .signup_date(localDate)
        .image_url(userSignupDto.getImage_url())
        .notification_status(userSignupDto.isNotification_status())
        .providerType(ProviderType.LOCAL)
        .roleType(RoleType.USER)
        .nick_name(userSignupDto.getNick_name())
        .build();

    userRepository.saveUser(user);

    return UserSignupResponseDto
        .builder()
        .user_id(user.getUser_id())
        .user_email(user.getUser_email())
        .user_name(user.getUser_name())
        .signup_date(user.getSignup_date())
        .nick_name(user.getNick_name())
        .build();
  }

  public UserLoginResponseDto login(UserLoginDto userLoginDto) {
    User user = userService.findByEmail(userLoginDto.getUser_email());

      if (user == null) {
          throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
      }

      if (!passwordEncoder.matches(userLoginDto.getPw(), user.getPw())) {
          throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
      }

    String accessToken = jwtTokenizer.createAccessToken(user.getUser_id(), user.getUser_email(),
        user.getUser_name(), RoleType.USER.getCode());
    String refreshToken = jwtTokenizer.createRefreshToken(user.getUser_id(), user.getUser_email(),
        user.getUser_name(), RoleType.USER.getCode());

    RefreshToken rToken = new RefreshToken(user.getUser_id(), refreshToken);
    refreshTokenService.saveRefreshToken(rToken);

    return UserLoginResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .user_id(user.getUser_id())
        .nick_name(user.getNick_name())
        .build();
  }


  public void addData(String authorizationHeader, UserAddDataDto userAddDataDto) {
    // 헤더 accessToken으로 사용자Id 가져옴
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }

    userRepository.addUserInfo(userId, userAddDataDto.getPhone_number(), userAddDataDto.getImage_url(), userAddDataDto.getNick_name());
  }
}
