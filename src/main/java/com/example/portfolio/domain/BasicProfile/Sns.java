package com.example.portfolio.domain.BasicProfile;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
@ToString(exclude = "basicProfile")
public class Sns {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serviceName;

  private String url;

  private String iconImageUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "basicProfile.id")
  private BasicProfile basicProfile;

  public static SnsBuilder builder(String serviceName, String url){
    return hiddenBuilder().serviceName(serviceName).url(url);
  }

  public void setBasicProfile(BasicProfile profile){
    basicProfile = profile;
  }
}
