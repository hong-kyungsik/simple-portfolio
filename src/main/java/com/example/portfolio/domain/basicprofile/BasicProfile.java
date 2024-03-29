package com.example.portfolio.domain.basicprofile;

import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.portfolio.Portfolio;
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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image.id")
  private Image profileImage;

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

  public void setProfileImage(Image image){
    profileImage = image;
  }

  public void isAddedTo(Portfolio portfolio) {
    this.portfolio = portfolio;
    portfolio.setBasicProfile(this);
  }
}
