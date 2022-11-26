package com.example.portfolio.api.v1.basicProfile.dto;

import com.example.portfolio.domain.basicprofile.Sns;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnsDtoV1 {
    private Long id;
    private String serviceName;
    private String url;

    public Sns toSns(){
        return Sns.builder(serviceName, url)
          .build();
    }

    public static SnsDtoV1 fromSns(Sns sns){
        SnsDtoV1 dtoV1 = new SnsDtoV1();
        dtoV1.id = sns.getId();
        dtoV1.serviceName = sns.getServiceName();
        dtoV1.url = sns.getUrl();
        return dtoV1;
    }
}
