package com.example.secu.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.httpBasic(basic->basic.disable())
            .csrf(csrf->csrf.disable())
            .cors(cors->cors.disable())
            .authorizeHttpRequests(request ->
                    request.requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/api/**").permitAll()
                            .requestMatchers("api/v1/user/join","/api/v1/users/login").permitAll())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(header->
                    header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
