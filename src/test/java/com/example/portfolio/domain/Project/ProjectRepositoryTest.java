package com.example.portfolio.domain.Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProjectRepositoryTest {

  @Autowired
  private ProjectRepository repository;

  private String title = "title";
  private String description = "description";
  private LocalDate startDate = LocalDate.now();
  private LocalDate endDate = LocalDate.now();

  private Project getProjectForTest(){
    return Project.builder(title, startDate, endDate)
        .description(description)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    Project project = getProjectForTest();
    repository.save(project);
    assertNotNull(project.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    Project project = getProjectForTest();
    repository.save(project);

    Project foundProject = repository.findById(project.getId()).orElseThrow(RuntimeException::new);
    assertEquals(title, foundProject.getTitle());
    assertEquals(description, foundProject.getDescription());
    assertEquals(startDate, foundProject.getStartDate());
    assertEquals(endDate, foundProject.getEndDate());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    Project project = getProjectForTest();
    repository.saveAndFlush(project);

    repository.delete(project);
    repository.flush();

    assertFalse(repository.findById(project.getId()).isPresent());
  }
}