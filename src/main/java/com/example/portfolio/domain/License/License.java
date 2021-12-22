package com.example.portfolio.domain.License;

import com.example.portfolio.domain.Portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
public class License {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String institutionName;

  private LocalDate startDate;

  private LocalDate expirationDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio.id")
  private Portfolio portfolio;

  public static LicenseBuilder builder(
      String title, String institutionName, LocalDate startDate, LocalDate expirationDate){
    return hiddenBuilder()
        .title(title).institutionName(institutionName).startDate(startDate).expirationDate(expirationDate);
  }
}
