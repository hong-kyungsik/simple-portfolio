package com.example.portfolio.api.v1.user.dto;

import com.example.portfolio.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinResponseDtoV1 {
  private Long id;
  private String email;
  private String name;

  public static UserJoinResponseDtoV1 makeFromUser(User user) {
    UserJoinResponseDtoV1 dto = new UserJoinResponseDtoV1();
    dto.setEmail(user.getEmail());
    dto.setName(user.getName());
    dto.setId(user.getId());
    return dto;
  }
}
