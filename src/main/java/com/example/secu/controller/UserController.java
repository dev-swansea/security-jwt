package com.example.secu.controller;

import com.example.secu.dto.User;
import com.example.secu.dto.UserJoinRequest;
import com.example.secu.dto.UserJoinResponse;
import com.example.secu.exception.Response;
import com.example.secu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/join")
  public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {

    User join = userService.join(userJoinRequest.toEntity(passwordEncoder.encode(userJoinRequest.getPassword())));
    UserJoinResponse userJoinResponse = new UserJoinResponse(join);
    return Response.success(userJoinResponse);

  }

}
