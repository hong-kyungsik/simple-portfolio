package com.example.portfolio.domain.BasicProfile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BasicProfileRepositoryTest {

  @Autowired
  BasicProfileRepository repository;

  @Test
  @DisplayName("저장")
  void save(){
    String name = "name";
    String phone = "phone";
    String email = "email";
    String profileImageUrl = "profileImageUrl";
    String selfDescription = "selfDescription";

    BasicProfile profile = BasicProfile.builder(name, phone, email)
        .profileImageUrl(profileImageUrl)
        .selfDescription(selfDescription)
        .build();

    repository.save(profile);

    assertNotNull(profile.getId());
  }

  @Test
  @DisplayName("아이디로 찾기")
  void findById(){
    String name = "name";
    String phone = "phone";
    String email = "email";
    String profileImageUrl = "profileImageUrl";
    String selfDescription = "selfDescription";

    BasicProfile profile = BasicProfile.builder(name, phone, email)
        .profileImageUrl(profileImageUrl)
        .selfDescription(selfDescription)
        .build();

    repository.save(profile);

    Optional<BasicProfile> optProfile = repository.findById(profile.getId());
    BasicProfile foundProfile = optProfile.orElseThrow(RuntimeException::new);
    assertEquals(name, foundProfile.getName());
    assertEquals(phone, foundProfile.getPhone());
    assertEquals(email, foundProfile.getEmail());
    assertEquals(profileImageUrl, foundProfile.getProfileImageUrl());
    assertEquals(selfDescription, foundProfile.getSelfDescription());
  }

  @Test
  @DisplayName("삭제하기")
  void delete(){
    String name = "name";
    String phone = "phone";
    String email = "email";
    String profileImageUrl = "profileImageUrl";
    String selfDescription = "selfDescription";

    BasicProfile profile = BasicProfile.builder(name, phone, email)
        .profileImageUrl(profileImageUrl)
        .selfDescription(selfDescription)
        .build();

    repository.saveAndFlush(profile);
    Long id = profile.getId();

    repository.delete(profile);
    repository.flush();

    assertFalse(repository.findById(id).isPresent());
  }
}