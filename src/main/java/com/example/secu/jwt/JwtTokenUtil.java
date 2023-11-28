package com.example.secu.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 암호화된 형태의 토큰이 생성된다.
 * JwtTokenUtil 는 문자열 형태의 토큰을 발급하기 위해 만든 클래스이다.
 * 인증된 사용자에게 위 토큰을 부여하고, 허가된 리소스에 접근할 수 있도록 한다.
 * 위 기능을 위해서 Jwts 클래스를 사용해야한다.
 */
public class JwtTokenUtil {
  public static String createToken(String userId, String secretKey, long expireTimeMs) {
    Claims claims = Jwts.claims();
    claims.put("userId", userId);

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }

}

