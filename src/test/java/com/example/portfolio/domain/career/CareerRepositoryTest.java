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
class CareerRepositoryTest {

  @Autowired
  private CareerRepository repository;

  @Test
  @DisplayName("저장")
  void save(){
    String companyName = "companyName";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Career career = Career.builder(companyName, startDate, endDate)
        .description(description)
        .build();

    repository.save(career);
    assertNotNull(career.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    String companyName = "companyName";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Career career = Career.builder(companyName, startDate, endDate)
        .description(description)
        .build();

    repository.save(career);

    Career foundCareer = repository.findById(career.getId()).orElseThrow(RuntimeException::new);
    assertEquals(companyName, foundCareer.getCompanyName());
    assertEquals(description, foundCareer.getDescription());
    assertEquals(startDate, foundCareer.getStartDate());
    assertEquals(endDate, foundCareer.getEndDate());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    String companyName = "companyName";
    String description = "description";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    Career career = Career.builder(companyName, startDate, endDate)
        .description(description)
        .build();

    repository.saveAndFlush(career);
    Long id = career.getId();

    repository.delete(career);
    repository.flush();
    assertFalse(repository.findById(id).isPresent());
  }
}