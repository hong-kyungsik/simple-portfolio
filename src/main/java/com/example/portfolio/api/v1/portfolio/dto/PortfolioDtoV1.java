package com.example.portfolio.api.v1.portfolio.dto;

import com.example.portfolio.api.v1.basicprofile.BasicProfileDtoV1;
import com.example.portfolio.api.v1.career.CareerDtoV1;
import com.example.portfolio.api.v1.education.EducationDtoV1;
import com.example.portfolio.api.v1.license.LicenseDtoV1;
import com.example.portfolio.api.v1.project.ProjectDtoV1;
import com.example.portfolio.api.v1.skill.SkillDtoV1;
import com.example.portfolio.api.v1.user.dto.UserDtoV1;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PortfolioDtoV1 {
    private Long id;
    private UserDtoV1 user;
    private BasicProfileDtoV1 basicProfile;
    private List<CareerDtoV1> careers;
    private List<EducationDtoV1> educations;
    private List<LicenseDtoV1> licenses;
    private List<ProjectDtoV1> projects;
    private List<SkillDtoV1> skills;
}
