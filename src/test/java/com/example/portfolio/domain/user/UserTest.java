package com.example.portfolio.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void createUserTest(){
    String name = "name";
    String email = "email@email.com";
    String password = "password";

    User user = User.builder(name, email, password)
        .build();

    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(password, user.getPassword());
  }
}