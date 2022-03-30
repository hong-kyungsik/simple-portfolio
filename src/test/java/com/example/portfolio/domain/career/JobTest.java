package com.example.portfolio.domain.career;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

  @Test
  void createTest(){
    String title = "title";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Job job = Job.builder(title, startDate, endDate)
        .description(description)
        .build();

    assertEquals(title, job.getTitle());
    assertEquals(description, job.getDescription());
    assertEquals(startDate, job.getStartDate());
    assertEquals(endDate, job.getEndDate());

  }
}