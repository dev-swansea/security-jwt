package com.example.secu.service;

import com.example.secu.dto.User;
import com.example.secu.exception.ErrorCode;
import com.example.secu.exception.HospitalReviewAppException;
import com.example.secu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public User join(User user) {
    userRepository.findByUserId(user.getUserId())
            .ifPresent( user1 -> {
              throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserId : %s",user1.getUserId()));
            });
    userRepository.save(user);

    return user;
  }

}
