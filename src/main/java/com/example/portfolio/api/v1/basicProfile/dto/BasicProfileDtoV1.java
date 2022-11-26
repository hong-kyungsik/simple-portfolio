package com.example.portfolio.api.v1.basicProfile.dto;

import com.example.portfolio.domain.basicprofile.BasicProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class BasicProfileDtoV1 {
    @NotNull
    private Long portfolioId;
    private Long id;

    @NotBlank
    @Size(min=1, max=200)
    private String name;

    @NotBlank
    private String selfDescription;

    @NotBlank
    @Size(min=1, max=20)
    private String phone;

    @Email
    @NotBlank
    @Size(min=1, max=200)
    private String email;

    private String profileImageUrl;
    private List<SnsDtoV1> snss = new ArrayList<>();

    public BasicProfile toBasicProfile(){
        BasicProfile basicProfile = BasicProfile.builder(name, phone, email)
          .selfDescription(selfDescription)
          .build();

        snss.stream()
          .map(SnsDtoV1::toSns)
          .forEach(basicProfile::addSns);

        return basicProfile;
    }

    public static BasicProfileDtoV1 fromBasicProfile(BasicProfile basicProfile){
        if(basicProfile==null){
            return null;
        }
        BasicProfileDtoV1 dtoV1 = new BasicProfileDtoV1();
        dtoV1.portfolioId = basicProfile.getId();
        dtoV1.id = basicProfile.getId();
        dtoV1.name = basicProfile.getName();
        dtoV1.selfDescription = basicProfile.getSelfDescription();
        dtoV1.phone = basicProfile.getPhone();
        dtoV1.email = basicProfile.getEmail();
        dtoV1.profileImageUrl = "/api/v1/images/"+
          basicProfile.getProfileImage().getFileName();
        dtoV1.snss = basicProfile.getSnss().stream()
          .map(SnsDtoV1::fromSns)
          .collect(Collectors.toList());
        return dtoV1;
    }
}
