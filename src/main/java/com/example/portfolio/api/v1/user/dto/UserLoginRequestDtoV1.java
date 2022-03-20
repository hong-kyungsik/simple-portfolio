package com.example.portfolio.api.v1.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginRequestDtoV1 {
    @Email
    @NotBlank
    @Size(min=1, max=200)
    private String email;

    @NotBlank
    @Size(min=1, max=50)
    private String password;
}
