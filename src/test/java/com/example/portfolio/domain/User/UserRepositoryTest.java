package com.example.portfolio.domain.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository repository;

  private String name = "name";
  private String email = "email";
  private String password = "password";

  private User getUserForTest(){
    return User.builder(name, email, password)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    User user = getUserForTest();
    repository.save(user);
    assertNotNull(user.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    User user = getUserForTest();
    repository.save(user);

    User foundUser = repository.findById(user.getId()).orElseThrow(RuntimeException::new);
    assertEquals(name, foundUser.getName());
    assertEquals(email, foundUser.getEmail());
    assertEquals(password, foundUser.getPassword());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    User user = getUserForTest();
    repository.saveAndFlush(user);

    repository.delete(user);
    repository.flush();

    assertFalse(repository.findById(user.getId()).isPresent());
  }
}