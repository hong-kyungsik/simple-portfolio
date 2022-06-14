package com.example.portfolio.domain.portfolio;

import com.example.portfolio.domain.basicprofile.BasicProfile;
import com.example.portfolio.domain.career.Career;
import com.example.portfolio.domain.education.Education;
import com.example.portfolio.domain.license.License;
import com.example.portfolio.domain.project.Project;
import com.example.portfolio.domain.skill.Skill;
import com.example.portfolio.domain.user.User;
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

  private boolean isPublic;

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
