package com.example.portfolio.api.v1.user;

import com.example.portfolio.api.v1.user.dto.UserJoinRequestDtoV1;
import com.example.portfolio.api.v1.user.dto.UserJoinResponseDtoV1;
import com.example.portfolio.api.v1.user.dto.UserLoginRequestDtoV1;
import com.example.portfolio.api.v1.user.dto.UserLoginResponseDtoV1;
import com.example.portfolio.domain.User.User;
import com.example.portfolio.security.TokenProvider;
import com.example.portfolio.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.example.portfolio.api.CommonResponse.ResultDto;
import static com.example.portfolio.api.CommonResponse.success;

@RestController
@RequestMapping("/api/v1/users/auth")
@RequiredArgsConstructor
@Slf4j
public class UserApiV1 {

  private final TokenProvider tokenProvider;
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final UserService userService;

  @PostMapping("/join")
  public ResultDto<UserJoinResponseDtoV1> join(
      @RequestBody @Valid UserJoinRequestDtoV1 dto
  ){
    User joinedUser = userService
        .join(dto.getName(), dto.getEmail(), dto.getPassword());

    return success(UserJoinResponseDtoV1.makeFromUser(joinedUser));
  }

  @PostMapping("/login")
  public ResultDto<UserLoginResponseDtoV1> login(
      @RequestBody @Valid UserLoginRequestDtoV1 dto,
      HttpServletResponse response
  ){
    User loginUser = userService.login(dto.getEmail(), dto.getPassword(), passwordEncoder);
    String token = tokenProvider.create(loginUser);

    response.setHeader("X-AUTH-TOKEN", token);
    Cookie cookie = new Cookie("X-AUTH_TOKEN", token);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    response.addCookie(cookie);

    return success(UserLoginResponseDtoV1.makeFromUser(loginUser));
  }

}
