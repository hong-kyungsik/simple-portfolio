package com.example.portfolio.api.v1.education;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EducationDtoV1 {
    private Long id;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
}
