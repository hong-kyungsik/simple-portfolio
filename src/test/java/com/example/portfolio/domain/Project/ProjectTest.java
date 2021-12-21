package com.example.portfolio.domain.Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
  @Test
  @DisplayName("project 생성 테스트")
  void createProjectTest(){
    String title = "title";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Project project = Project.builder()
        .title(title)
        .description(description)
        .startDate(startDate)
        .endDate(endDate)
        .build();

    assertEquals(title, project.getTitle());
    assertEquals(description, project.getDescription());
    assertEquals(startDate, project.getStartDate());
    assertEquals(endDate, project.getEndDate());
  }
}