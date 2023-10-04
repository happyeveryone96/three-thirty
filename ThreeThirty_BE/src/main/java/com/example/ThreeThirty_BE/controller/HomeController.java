package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.security.jwt.util.IfLogin;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import com.example.ThreeThirty_BE.security.jwt.util.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

  private final JwtTokenizer jwtTokenizer;

  @GetMapping("/test")
  public LoginUser argumentsTest(@IfLogin LoginUser loginUser) {
    return loginUser;
  }

  @GetMapping("/test2")
  public String userCheck() {
    return "User Check ok";
  }
}
