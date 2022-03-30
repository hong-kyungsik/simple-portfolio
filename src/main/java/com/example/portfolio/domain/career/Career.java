package com.example.portfolio.domain.career;

import com.example.portfolio.domain.portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
public class Career {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;

  private String description;

  private LocalDate startDate;

  private LocalDate endDate;

  @OneToMany(mappedBy = "career")
  private List<Job> jobs;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio.id")
  private Portfolio portfolio;

  public static CareerBuilder builder(String companyName, LocalDate startDate, LocalDate endDate){
    return hiddenBuilder().companyName(companyName).startDate(startDate).endDate(endDate);
  }
}
