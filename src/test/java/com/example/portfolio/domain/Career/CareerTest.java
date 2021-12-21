package com.example.portfolio.domain.Career;

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

    Career career = Career.builder()
        .companyName(companyName)
        .description(description)
        .startDate(startDate)
        .endDate(endDate)
        .build();

    assertEquals(companyName, career.getCompanyName());
    assertEquals(description, career.getDescription());
    assertEquals(startDate, career.getStartDate());
    assertEquals(endDate, career.getEndDate());

  }
}