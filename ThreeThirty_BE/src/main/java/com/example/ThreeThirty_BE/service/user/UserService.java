package com.example.ThreeThirty_BE.service.user;



import com.example.ThreeThirty_BE.domain.RoleType;
import com.example.ThreeThirty_BE.domain.User;
import com.example.ThreeThirty_BE.dto.login.UserLoginResponseDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupResponseDto;
import com.example.ThreeThirty_BE.dto.user.UserUpdateDto;
import com.example.ThreeThirty_BE.dto.user.UserUpdateResponseDto;
import com.example.ThreeThirty_BE.mapper.UserRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenizer jwtTokenizer;

  @Transactional
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Transactional
  public User findById(Long userId) {
    return userRepository.findById(userId);
  }


  @Transactional
  public UserUpdateResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto) {
    userRepository.updateUser(userId, userUpdateDto.getUsername());
//        String EncodePassword = passwordEncoder.encode(userUpdateDto.getPassword());
//        userRepository.updateUserPassword(userId, EncodePassword);

    User findUser = this.findById(userId);
    return UserUpdateResponseDto
        .builder()
        .username(findUser.getUsername())
        .build();
  }

  @Transactional
  public void updatePassword(Long userId, String password) {
    String EncodePassword = passwordEncoder.encode(password);
    userRepository.updateUserPassword(userId, EncodePassword);
  }

  @Transactional
  public UserSignupResponseDto currentUser(Long userId) {
    User findUser = this.findById(userId);
    return UserSignupResponseDto
        .builder()
        .userId(findUser.getUserId())
        .email(findUser.getEmail())
        .username(findUser.getUsername())
        .build();
  }

  @Transactional
  public UserLoginResponseDto reissuingToken(String authorizationHeader) {

    Long id = jwtTokenizer.getUserIdFromToken(authorizationHeader);
    User user = userRepository.findById(id);

    String accessToken = jwtTokenizer.createAccessToken(user.getUserId(), user.getEmail(), user.getUsername(),
        RoleType.USER.getCode());

    String token = authorizationHeader.replace("Bearer ", "");

    return UserLoginResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(token)
        .userId(user.getUserId())
        .name(user.getUsername())
        .build();
  }
}
