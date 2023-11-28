package com.example.secu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {

  private String userId;
  private String userName;

  public UserJoinResponse(User user) {
    this.userId = user.getUserId();
    this.userName = user.getUserName();
  }

}
