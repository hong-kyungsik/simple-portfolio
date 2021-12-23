package com.example.portfolio.domain.Education;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EducationTest {

  @Test
  void createTest(){
    String institutionName = "institutionName";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Education education = Education.builder(institutionName, startDate, endDate)
        .build();

    assertEquals(institutionName, education.getInstitutionName());
    assertEquals(startDate, education.getStartDate());
    assertEquals(endDate, education.getEndDate());

  }
}