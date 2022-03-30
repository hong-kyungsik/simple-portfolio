package com.example.portfolio.domain.project;

import com.example.portfolio.domain.portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  private LocalDate startDate;

  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio.id")
  private Portfolio portfolio;

  public static ProjectBuilder builder(String title, LocalDate startDate, LocalDate endDate){
    return hiddenBuilder().title(title).startDate(startDate).endDate(endDate);
  }

  public void addDescription(String description){
    this.description = description;
  }
}
