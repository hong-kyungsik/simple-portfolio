package com.example.portfolio.domain.skill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SkillRepositoryTest {

  @Autowired
  private SkillRepository repository;

  private String title = "title";
  private Integer levelPercentage = 90;

  private Skill getSkillForTest(){
    return Skill.builder(title)
        .levelPercentage(levelPercentage)
        .build();
  }

  @Test
  @DisplayName("저장")
  void save(){
    Skill skill = getSkillForTest();
    repository.save(skill);
    assertNotNull(skill.getId());
  }

  @Test
  @DisplayName("아이디로 조회")
  void findById(){
    Skill skill = getSkillForTest();
    repository.save(skill);

    Skill foundSkill = repository.findById(skill.getId()).orElseThrow(RuntimeException::new);
    assertEquals(title, foundSkill.getTitle());
    assertEquals(levelPercentage, foundSkill.getLevelPercentage());
  }

  @Test
  @DisplayName("삭제")
  void delete(){
    Skill skill = getSkillForTest();
    repository.saveAndFlush(skill);

    repository.delete(skill);
    repository.flush();
    assertFalse(repository.findById(skill.getId()).isPresent());
  }
}