package com.example.portfolio.api.v1.basicProfile;

import com.example.portfolio.api.v1.basicProfile.dto.BasicProfileDtoV1;
import com.example.portfolio.domain.basicprofile.BasicProfile;
import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.security.TokenProvider;
import com.example.portfolio.service.basicProfile.BasicProfileService;
import com.example.portfolio.service.image.ImageService;
import com.example.portfolio.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
  controllers = BasicProfileApiV1.class,
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
  }
)
@AutoConfigureMockMvc(addFilters = false)
class BasicProfileApiV1Test {

  private final String baseUrl = "/api/v1/basic-profiles";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BasicProfileService service;

  @MockBean
  private ImageService imageService;

  @MockBean
  private UserService userService;

  @MockBean
  private TokenProvider tokenProvider;

  private Long portfolioId = 1L;
  private String name = "new name";
  private String selfDescription = "This is a content of new self description";
  private String phone = "010-2000-3000";
  private String email = "aaa@bbb.com";

  public BasicProfileDtoV1 createNewBasicProfile(){
    BasicProfileDtoV1 basicProfileDtoV1 = new BasicProfileDtoV1();
    basicProfileDtoV1.setPortfolioId(1L);
    basicProfileDtoV1.setName(name);
    basicProfileDtoV1.setSelfDescription(selfDescription);
    basicProfileDtoV1.setPhone(phone);
    basicProfileDtoV1.setEmail(email);
    return basicProfileDtoV1;
  }

  private MockMultipartFile createMockFileForTest(){
    return new MockMultipartFile(
      "profileImage",
      "temp.txt",
      MediaType.TEXT_PLAIN_VALUE,
      "Temp text file for test".getBytes(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("기본 프로필 생성에 성공한다.")
  void addNewBasicProfileToPortfolio() throws Exception {
    BasicProfileDtoV1 bodyDto = createNewBasicProfile();
    MockMultipartFile fileDto = createMockFileForTest();
    User mockUser = User.builder("test", "test@test.com", "password")
      .build();
    Image mockImage = Image.builder(fileDto.getName(), fileDto.getOriginalFilename(), mockUser)
        .build();
    BasicProfile mockBasicProfile = BasicProfile.builder(bodyDto.getName(), bodyDto.getPhone(), bodyDto.getEmail())
        .build();
    mockBasicProfile.setProfileImage(mockImage);

    when(userService.getUserByUserId(any()))
      .thenReturn(mockUser);
    when(imageService.storeImage(fileDto, mockUser))
      .thenReturn(mockImage);
    when(service.addBasicProfile(
      eq(1L),
      any(),
      eq(mockImage)))
      .thenReturn(mockBasicProfile);

    MockPart jsonMockPart = new MockPart("basicProfile", ("{\n" +
      "\t\"portfolioId\": 1,\n" +
      "\t\"name\": \"new name\",\n" +
      "\t\"selfDescription\": \"This is a content of new self description\",\n" +
      "\t\"phone\": \"010-2000-3000\",\n" +
      "\t\"email\": \"aaa@bbb.com\"\n" +
      "}").getBytes());

    jsonMockPart.getHeaders().setContentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(
      multipart(baseUrl)
        .part(jsonMockPart)
        .file(fileDto)
    ).andExpect(status().isOk())
      .andExpect(jsonPath("$.success", equalTo(true)))
      .andExpect(jsonPath("$.error", nullValue()))
      .andExpect(jsonPath("$.data", notNullValue()));
  }
}