package com.example.secu.controller;

import com.example.secu.dto.User;
import com.example.secu.dto.UserJoinRequest;
import com.example.secu.dto.UserJoinResponse;
import com.example.secu.exception.Response;
import com.example.secu.jwt.dto.UserLoginRequest;
import com.example.secu.jwt.dto.UserLoginResponse;
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

  /**
   * 정상적으로, 로그인 인증이 되었으면 token 값이 반환될 것이다.
   * 결과적으로 token 을 UserLoginResponse 객체에 담은 뒤, 반환한다.
   *
   * @param request userId, password
   * @return token
   */
  @PostMapping("/login")
  public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
    String token = userService.login(request.getUserId(), request.getPassword());
    return Response.success(new UserLoginResponse(token));
  }
}
