package com.example.ThreeThirty_BE.controller;

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

  // 로그인
  @PostMapping("/login")
  public UserLoginResponseDto login(@RequestBody UserLoginDto userLoginDto) {
    return loginService.login(userLoginDto);
  }

  // 로그아웃
  @PostMapping("/logout")
  public ResponseEntity logout(@RequestHeader("Authorization") String authorizationHeader) {
    String token = authorizationHeader.replace("Bearer ", "");
    refreshTokenService.deleteRefreshToken(token);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
