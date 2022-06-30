package com.example.portfolio.domain.basicprofile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnsTest {

  @Test
  void createTest(){
    String serviceName = "serviceName";
    String url = "url";
    String iconImageUrl = "iconImageUrl";

    Sns sns = Sns.builder(serviceName, url)
        .iconImageUrl(iconImageUrl)
        .build();

    assertEquals(serviceName, sns.getServiceName());
    assertEquals(url, sns.getUrl());
    assertEquals(iconImageUrl, sns.getIconImageUrl());
  }
}