package com.example.portfolio.domain.Portfolio;

import com.example.portfolio.domain.BasicProfile.BasicProfile;
import com.example.portfolio.domain.Career.Career;
import com.example.portfolio.domain.Education.Education;
import com.example.portfolio.domain.License.License;
import com.example.portfolio.domain.Project.Project;
import com.example.portfolio.domain.Skill.Skill;
import com.example.portfolio.domain.User.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Portfolio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user.id")
  private User user;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private BasicProfile basicProfile;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private List<Career> careers;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private List<Education> educations;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private List<License> licenses;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private List<Project> projects;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private List<Skill> skills;
}
