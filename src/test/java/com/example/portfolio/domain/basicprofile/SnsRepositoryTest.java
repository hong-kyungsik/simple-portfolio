package com.example.portfolio.domain.basicprofile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SnsRepositoryTest {

  @Autowired
  private SnsRepository repository;

  private String serviceName = "serviceName";
  private String url = "url";
  private String iconImageUrl = "iconImageUrl";

  private Sns getSnsForTest(){
    return Sns.builder(serviceName, url)
        .iconImageUrl(iconImageUrl)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    Sns sns = getSnsForTest();
    repository.save(sns);
    assertNotNull(sns);
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    Sns sns = getSnsForTest();
    repository.save(sns);

    Sns foundSns = repository.findById(sns.getId()).orElseThrow(RuntimeException::new);
    assertEquals(serviceName, foundSns.getServiceName());
    assertEquals(url, foundSns.getUrl());
    assertEquals(iconImageUrl, foundSns.getIconImageUrl());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    Sns sns = getSnsForTest();
    repository.saveAndFlush(sns);

    repository.delete(sns);
    repository.flush();

    assertFalse(repository.findById(sns.getId()).isPresent());
  }
}