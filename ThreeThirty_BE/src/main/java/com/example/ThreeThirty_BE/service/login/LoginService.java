package com.example.ThreeThirty_BE.service.login;


import com.example.ThreeThirty_BE.domain.ProviderType;
import com.example.ThreeThirty_BE.domain.RefreshToken;
import com.example.ThreeThirty_BE.domain.RoleType;
import com.example.ThreeThirty_BE.domain.User;
import com.example.ThreeThirty_BE.dto.login.UserLoginDto;
import com.example.ThreeThirty_BE.dto.login.UserLoginResponseDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupResponseDto;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.UserRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import com.example.ThreeThirty_BE.service.user.UserService;
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
      if (userRepository.findByEmail(userSignupDto.getEmail()) != null) {
          throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
      }

    User user = User.builder()
        .email(userSignupDto.getEmail())
        .username(userSignupDto.getUsername())
        .password(passwordEncoder.encode(userSignupDto.getPassword()))
        .providerType(ProviderType.LOCAL)
        .roleType(RoleType.USER)
        .build();

    userRepository.saveUser(user);

    return UserSignupResponseDto
        .builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .username(user.getUsername())
        .build();
  }

  public UserLoginResponseDto login(UserLoginDto userLoginDto) {
    User user = userService.findByEmail(userLoginDto.getEmail());
      if (user == null) {
          throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
      }

      if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
          throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
      }

    String accessToken = jwtTokenizer.createAccessToken(user.getUserId(), user.getEmail(),
        user.getUsername(), RoleType.USER.getCode());
    String refreshToken = jwtTokenizer.createRefreshToken(user.getUserId(), user.getEmail(),
        user.getUsername(), RoleType.USER.getCode());

    RefreshToken rToken = new RefreshToken(user.getUserId(), refreshToken);
    refreshTokenService.saveRefreshToken(rToken);

    return UserLoginResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .userId(user.getUserId())
        .name(user.getUsername())
        .build();
  }


}
