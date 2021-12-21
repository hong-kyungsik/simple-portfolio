package com.example.portfolio.domain.Career;

import org.junit.jupiter.api.Test;

import javax.persistence.JoinColumn;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

  @Test
  void createTest(){
    String title = "title";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Job job = Job.builder()
        .title(title)
        .description(description)
        .startDate(startDate)
        .endDate(endDate)
        .build();

    assertEquals(title, job.getTitle());
    assertEquals(description, job.getDescription());
    assertEquals(startDate, job.getStartDate());
    assertEquals(endDate, job.getEndDate());

  }
}