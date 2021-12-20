package com.example.portfolio.domain.Portfolio;

import com.example.portfolio.domain.BasicProfile.BasicProfile;
import com.example.portfolio.domain.Career.Career;
import com.example.portfolio.domain.Education.Education;
import com.example.portfolio.domain.License.License;
import com.example.portfolio.domain.Project.Project;
import com.example.portfolio.domain.Skill.Skill;
import com.example.portfolio.domain.User.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Portfolio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id")
  private User user;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "portfolio")
  private BasicProfile basicProfile;

  @OneToMany
  private List<Career> careers;

  @OneToMany
  private List<Education> educations;

  @OneToMany
  private List<License> licenses;

  @OneToMany
  private List<Project> projects;

  @OneToMany
  private List<Skill> skills;
}
