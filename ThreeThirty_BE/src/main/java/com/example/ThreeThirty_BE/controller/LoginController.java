package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.dto.login.UserAddDataDto;
import com.example.ThreeThirty_BE.dto.login.UserLoginDto;
import com.example.ThreeThirty_BE.dto.login.UserLoginResponseDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupResponseDto;
import com.example.ThreeThirty_BE.service.login.LoginService;
import com.example.ThreeThirty_BE.service.login.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class LoginController {

  private final LoginService loginService;
  private final RefreshTokenService refreshTokenService;

  // 회원 가입
  @PostMapping
  public ResponseEntity register(@RequestBody UserSignupDto userSignupDto) {
    UserSignupResponseDto userSignupResponseDto = loginService.saveUser(userSignupDto);
    return new ResponseEntity<>(userSignupResponseDto, HttpStatus.CREATED);
  }

  // 소셜 로그인 이용자 : 신규 가입 시 전화번호, 프로필이미지 추가 정보
  @PostMapping("/add-data")
  public ResponseEntity addData(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserAddDataDto userAddDataDto){
    loginService.addData(authorizationHeader, userAddDataDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 로그인
  //error message 고칠 필요 있음. --> 아이디를 잘 못 입력했는데 아이디 잘못 입력 에러가 아닌 다른 에러가 나와서
  // 찾기가 힘듬
  @PostMapping("/login")
  public UserLoginResponseDto login(@RequestBody UserLoginDto userLoginDto) {
    return loginService.login(userLoginDto);
  }

  // 로그아웃
  @PostMapping("/logout")
  public ResponseEntity logout(@RequestHeader("Authorization") String authorizationHeader) {
    refreshTokenService.deleteRefreshToken(authorizationHeader);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
