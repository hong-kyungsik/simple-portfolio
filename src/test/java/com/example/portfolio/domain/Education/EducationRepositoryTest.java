package com.example.portfolio.domain.Education;

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
class EducationRepositoryTest {

  @Autowired
  private EducationRepository repository;

  private String institutionName = "institutionName";
  private LocalDate startDate = LocalDate.now();
  private LocalDate endDate = LocalDate.now();

  private Education getEducationForTest(){
    return Education.builder(institutionName, startDate, endDate).build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    Education education = getEducationForTest();

    repository.save(education);

    assertNotNull(education.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    Education education = getEducationForTest();
    repository.save(education);

    Education foundEducation = repository.findById(education.getId()).orElseThrow(RuntimeException::new);
    assertEquals(institutionName, foundEducation.getInstitutionName());
    assertEquals(startDate, foundEducation.getStartDate());
    assertEquals(endDate, foundEducation.getEndDate());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    Education education = getEducationForTest();
    repository.saveAndFlush(education);

    repository.delete(education);
    repository.flush();

    assertFalse(repository.findById(education.getId()).isPresent());
  }

}