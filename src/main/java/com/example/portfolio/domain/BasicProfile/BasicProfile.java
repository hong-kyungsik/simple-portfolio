package com.example.portfolio.domain.BasicProfile;

import com.example.portfolio.domain.Portfolio.Portfolio;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
public class BasicProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String selfDescription;

  private String phone;

  private String email;

  private String profileImageUrl;

  @OneToMany(mappedBy = "basicProfile", cascade = CascadeType.ALL)
  private final List<Sns> snss = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio.id")
  private Portfolio portfolio;

  public static BasicProfileBuilder builder(String name, String phone, String email){
    return hiddenBuilder().name(name).phone(phone).email(email);
  }

  public void addSns(Sns sns){
    snss.add(sns);
    sns.setBasicProfile(this);
  }
}
