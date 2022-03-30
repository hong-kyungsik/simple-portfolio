package com.example.portfolio.domain.skill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {
  @Test
  void createSkillTest(){
    String title = "title";
    Integer levelPercentage = 30;

    Skill skill = Skill.builder(title)
        .levelPercentage(levelPercentage)
        .build();

    assertEquals(title, skill.getTitle());
    assertEquals(levelPercentage, skill.getLevelPercentage());
  }

}