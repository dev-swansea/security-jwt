package com.example.secu.service;

import com.example.secu.dto.User;
import com.example.secu.exception.ErrorCode;
import com.example.secu.exception.HospitalReviewAppException;
import com.example.secu.jwt.JwtTokenUtil;
import com.example.secu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.token.secret}")
  private String secretKey;
  private long expiredTimeMs = 1000 * 60 * 60;

  @Transactional
  public User join(User user) {
    userRepository.findByUserId(user.getUserId())
            .ifPresent(user1 -> {
              throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserId : %s", user1.getUserId()));
            });
    userRepository.save(user);

    return user;
  }

  public String login(String userId, String password) {
    System.out.println(userId);
    User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, String.format("%s는 없는 유저입니다.", userId)));
    System.out.println("user => ");
    System.out.println(user);
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, String.format("username 또는 password가 잘못되었습니다."));
    }

    return JwtTokenUtil.createToken(userId, secretKey, expiredTimeMs);
  }

}
