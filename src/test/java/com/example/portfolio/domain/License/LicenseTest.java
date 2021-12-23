package com.example.portfolio.domain.License;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LicenseTest {

  @Test
  void createTest(){
    String title = "title";
    String institutionName = "institutionName";
    LocalDate startDate = LocalDate.now();
    LocalDate expirationDate = LocalDate.now();

    License license = License.builder(title, institutionName, startDate, expirationDate)
        .build();

    assertEquals(title, license.getTitle());
    assertEquals(institutionName, license.getInstitutionName());
    assertEquals(startDate, license.getStartDate());
    assertEquals(expirationDate, license.getExpirationDate());

  }
}