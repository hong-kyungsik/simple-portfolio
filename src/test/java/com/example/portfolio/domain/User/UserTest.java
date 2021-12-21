package com.example.portfolio.domain.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void createUserTest(){
    String name = "name";
    String email = "email@email.com";
    String password = "password";

    User user = User.builder()
        .name(name)
        .email(email)
        .password(password)
        .build();

    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(password, user.getPassword());
  }
}