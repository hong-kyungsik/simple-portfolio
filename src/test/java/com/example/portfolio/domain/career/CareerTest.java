package com.example.portfolio.domain.career;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CareerTest {

  @Test
  void createTest(){
    String companyName = "companyName";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Career career = Career.builder(companyName, startDate, endDate)
        .description(description)
        .build();

    assertEquals(companyName, career.getCompanyName());
    assertEquals(description, career.getDescription());
    assertEquals(startDate, career.getStartDate());
    assertEquals(endDate, career.getEndDate());

  }
}