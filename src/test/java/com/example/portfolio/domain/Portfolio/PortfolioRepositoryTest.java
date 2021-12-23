package com.example.portfolio.domain.Portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PortfolioRepositoryTest {

  @Test
  @DisplayName("저장")
  void save(){

  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){}

  @Test
  @DisplayName("삭제")
  void delete(){}
}