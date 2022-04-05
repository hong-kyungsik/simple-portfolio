package com.example.portfolio.api.v1.basicprofile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnsDtoV1 {
    private Long id;
    private String serviceName;
    private String url;
    private String iconImageUrl;
}
