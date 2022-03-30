package com.example.portfolio.api.v1.user.dto;

import com.example.portfolio.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginResponseDtoV1 {
    private Long id;
    private String email;
    private String name;

    public static UserLoginResponseDtoV1 makeFromUser(User user){
        return UserLoginResponseDtoV1.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .build();
    }
}
