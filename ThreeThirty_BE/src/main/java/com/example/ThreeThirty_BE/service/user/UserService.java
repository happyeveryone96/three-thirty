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
  public User findByEmail(String user_email) {
    User user = userRepository.findByEmail(user_email);
    System.out.println(user.getUser_id());
    System.out.println(user.getUser_email());
    System.out.println(user.getUser_name());
    System.out.println(user.getProviderType());
    System.out.println(user.getRoleType());
    System.out.println(user.getPw());
    System.out.println(user.getPhone_number());
    return userRepository.findByEmail(user_email);
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

    User findTBUSER = this.findById(userId);
    return UserUpdateResponseDto
        .builder()
        .username(findTBUSER.getUser_name())
        .build();
  }

  @Transactional
  public void updatePassword(Long userId, String password) {
    String EncodePassword = passwordEncoder.encode(password);
    userRepository.updateUserPassword(userId, EncodePassword);
  }



  @Transactional
  public UserSignupResponseDto currentUser(Long userId) {
    User findTBUSER = this.findById(userId);
    return UserSignupResponseDto
        .builder()
        .user_id(findTBUSER.getUser_id())
        .user_email(findTBUSER.getUser_email())
        .user_name(findTBUSER.getUser_email())
        .build();
  }

  @Transactional
  public UserLoginResponseDto reissuingToken(String authorizationHeader) {

    Long id = jwtTokenizer.getUserIdFromToken(authorizationHeader);
    User user = userRepository.findById(id);

    String accessToken = jwtTokenizer.createAccessToken(user.getUser_id(), user.getUser_email(), user.getUser_name(),
        RoleType.USER.getCode());

    String token = authorizationHeader.replace("Bearer ", "");

    return UserLoginResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(token)
        .user_id(user.getUser_id())
        .user_name(user.getUser_name())
        .build();
  }
}
