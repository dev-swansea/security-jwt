package com.example.secu.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

  private String userId;
  private String password;

}

// 로그인 과정에서는 userId 와 password 만 사용자에게 받으면 된다.