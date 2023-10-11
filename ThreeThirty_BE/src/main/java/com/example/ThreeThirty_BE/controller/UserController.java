package com.example.ThreeThirty_BE.controller;


import com.example.ThreeThirty_BE.dto.login.UserLoginResponseDto;
import com.example.ThreeThirty_BE.dto.login.UserSignupResponseDto;
import com.example.ThreeThirty_BE.dto.user.UserPasswordDto;
import com.example.ThreeThirty_BE.dto.user.UserUpdateDto;
import com.example.ThreeThirty_BE.dto.user.UserUpdateResponseDto;
import com.example.ThreeThirty_BE.security.jwt.util.IfLogin;
import com.example.ThreeThirty_BE.security.jwt.util.LoginUser;
import com.example.ThreeThirty_BE.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  // 회원 정보
  @GetMapping
  public ResponseEntity currentUser(@IfLogin LoginUser loginUser) {
    UserSignupResponseDto userSignupResponseDto = userService.currentUser(loginUser.getUserId());
    return new ResponseEntity<>(userSignupResponseDto, HttpStatus.OK);
  }

  // 회원 nick_name 수정
  @PutMapping
  public ResponseEntity updateUser(@IfLogin LoginUser loginUser, @RequestBody @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UserUpdateResponseDto userUpdateResponseDto = userService.updateUser(loginUser.getUserId(),
        userUpdateDto);
    return new ResponseEntity<>(userUpdateResponseDto, HttpStatus.OK);
  }

  // 회원 password 수정
  @PutMapping("/password")
  public void updatePassword(@IfLogin LoginUser loginUser,
      @RequestBody UserPasswordDto userPasswordDto) {
    userService.updatePassword(loginUser.getUserId(), userPasswordDto.getPassword());
  }

  @PostMapping("/refreshToken")
  public ResponseEntity requestRefresh(@RequestHeader("Authorization") String authorizationHeader) {
    UserLoginResponseDto userLoginResponseDto = userService.reissuingToken(authorizationHeader);
    return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
  }

}
