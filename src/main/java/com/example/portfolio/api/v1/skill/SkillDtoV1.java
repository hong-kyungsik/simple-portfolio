package com.example.portfolio.api.v1.skill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDtoV1 {
    private Long id;
    private String title;
    private Integer levelPercentage;
}
