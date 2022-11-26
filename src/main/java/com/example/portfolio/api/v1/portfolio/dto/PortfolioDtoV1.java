package com.example.portfolio.api.v1.portfolio.dto;

import com.example.portfolio.api.v1.basicProfile.dto.BasicProfileDtoV1;
import com.example.portfolio.api.v1.career.CareerDtoV1;
import com.example.portfolio.api.v1.education.EducationDtoV1;
import com.example.portfolio.api.v1.license.LicenseDtoV1;
import com.example.portfolio.api.v1.project.ProjectDtoV1;
import com.example.portfolio.api.v1.skill.SkillDtoV1;
import com.example.portfolio.api.v1.user.dto.UserDtoV1;
import com.example.portfolio.domain.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PortfolioDtoV1 {
    private Long id;
    private boolean isPublic;
    private UserDtoV1 user;
    private BasicProfileDtoV1 basicProfile;
    private List<CareerDtoV1> careers;
    private List<EducationDtoV1> educations;
    private List<LicenseDtoV1> licenses;
    private List<ProjectDtoV1> projects;
    private List<SkillDtoV1> skills;

    public static PortfolioDtoV1 fromPortfolio(Portfolio portfolio){
        PortfolioDtoV1 dto = new PortfolioDtoV1();
        dto.id = portfolio.getId();
        dto.isPublic = portfolio.isPublic();
        dto.basicProfile = BasicProfileDtoV1
          .fromBasicProfile(portfolio.getBasicProfile());
        return dto;
    }
}
