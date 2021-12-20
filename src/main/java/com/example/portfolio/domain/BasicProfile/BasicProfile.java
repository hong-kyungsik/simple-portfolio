package com.example.portfolio.domain.BasicProfile;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class BasicProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String selfDescription;

  private String phone;

  private String email;

  private String profileImageUrl;

  @OneToMany(mappedBy = "basicProfile")
  private List<Sns> snses;
}
