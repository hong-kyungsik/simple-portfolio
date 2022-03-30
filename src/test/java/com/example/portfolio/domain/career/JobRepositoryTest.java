package com.example.portfolio.domain.career;

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
class JobRepositoryTest {

  @Autowired
  private JobRepository repository;

  private String title = "title";
  private String description = "description";
  private LocalDate startDate = LocalDate.now();
  private LocalDate endDate = LocalDate.now();

  private Job getJobForTest(){
    return Job.builder(title, startDate, endDate)
        .description(description)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    Job job = getJobForTest();
    repository.save(job);
    assertNotNull(job.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    Job job = getJobForTest();
    repository.save(job);

    Job foundJob = repository.findById(job.getId()).orElseThrow(RuntimeException::new);
    assertEquals(title, foundJob.getTitle());
    assertEquals(description, foundJob.getDescription());
    assertEquals(startDate, foundJob.getStartDate());
    assertEquals(endDate, foundJob.getEndDate());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    Job job = getJobForTest();
    repository.saveAndFlush(job);

    repository.delete(job);
    repository.flush();
    assertFalse(repository.findById(job.getId()).isPresent());
  }
}