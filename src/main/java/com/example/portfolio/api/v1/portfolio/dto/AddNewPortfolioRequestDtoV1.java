package com.example.portfolio.api.v1.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddNewPortfolioRequestDtoV1 {
    @Email
    @NotBlank
    @Size(min=1, max=200)
    private String userEmail;
}
