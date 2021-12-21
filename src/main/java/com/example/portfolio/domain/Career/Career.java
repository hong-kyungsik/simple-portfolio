package com.example.portfolio.domain.Career;

import com.example.portfolio.domain.Portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;
import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
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
}
