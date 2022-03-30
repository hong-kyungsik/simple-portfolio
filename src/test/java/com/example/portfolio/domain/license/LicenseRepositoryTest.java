package com.example.portfolio.domain.license;

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
class LicenseRepositoryTest {

  @Autowired
  private LicenseRepository repository;

  private String title = "title";
  private String institutionName = "institutionName";
  private LocalDate startDate = LocalDate.now();
  private LocalDate expirationDate = LocalDate.now();

  private License getLicenseForTest(){
    return License.builder(title, institutionName, startDate, expirationDate)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    License license = getLicenseForTest();
    repository.save(license);

    assertNotNull(license.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    License license = getLicenseForTest();
    repository.save(license);

    License foundLicense = repository.findById(license.getId()).orElseThrow(RuntimeException::new);
    assertEquals(title, foundLicense.getTitle());
    assertEquals(institutionName, foundLicense.getInstitutionName());
    assertEquals(startDate, foundLicense.getStartDate());
    assertEquals(expirationDate, foundLicense.getExpirationDate());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    License license = getLicenseForTest();
    repository.saveAndFlush(license);

    repository.delete(license);
    repository.flush();

    assertFalse(repository.findById(license.getId()).isPresent());
  }
}