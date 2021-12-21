package com.example.portfolio.domain.BasicProfile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicProfileTest {
  @Test
  void createTest(){
    String name = "name";
    String selfDescription = "selfDescription";
    String phone = "01000000000";
    String email = "aaa@bbb.ccc";
    String profileImageUrl = "https://profileImageUrl";

    BasicProfile profile = BasicProfile.builder()
        .name(name)
        .selfDescription(selfDescription)
        .phone(phone)
        .email(email)
        .profileImageUrl(profileImageUrl)
        .build();

    assertEquals(name, profile.getName());
    assertEquals(selfDescription, profile.getSelfDescription());
    assertEquals(phone, profile.getPhone());
    assertEquals(email, profile.getEmail());
    assertEquals(profileImageUrl, profile.getProfileImageUrl());
  }
}