package com.example.portfolio.api.v1.basicprofile.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasicProfileDtoV1 {
    private Long id;
    private String name;
    private String selfDescription;
    private String phone;
    private String email;
    private String profileImageUrl;
    private List<SnsDtoV1> snss = new ArrayList<>();
}
