package com.example.portfolio.domain.Skill;

import com.example.portfolio.domain.Portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private Integer levelPercentage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio.id")
  private Portfolio portfolio;

}
