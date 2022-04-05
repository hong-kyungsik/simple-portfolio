package com.example.portfolio.api.v1.career;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CareerDtoV1 {
    private String id;
    private String companyName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<JobDtoV1> jobs;
}
