package com.example.portfolio.domain.BasicProfile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Sns {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serviceName;

  private String url;

  private String iconImageUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private BasicProfile basicProfile;
}
