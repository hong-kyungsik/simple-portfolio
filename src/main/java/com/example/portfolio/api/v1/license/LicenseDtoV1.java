package com.example.portfolio.api.v1.license;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LicenseDtoV1 {
    private Long id;
    private String title;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate expirationDate;
}
