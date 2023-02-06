package com.example.portfolio.api.v1.portfolio;

import com.example.portfolio.api.v1.basicProfile.dto.BasicProfileDtoV1;
import com.example.portfolio.domain.basicprofile.BasicProfile;
import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.security.TokenProvider;
import com.example.portfolio.service.portfolio.PortfolioService;
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

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
    controllers = PortfolioApiV1.class,
    excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
    }
)
@AutoConfigureMockMvc(addFilters = false)
class PortfolioApiV1Test {

    private final String baseUrl = "/api/v1/portfolios";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PortfolioService portfolioService;

    @MockBean
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("신규 포트폴리오 생성이 성공한다.")
    void addNewPortfolioTest() throws Exception {
        Portfolio mockPortfolio = Portfolio.builder()
          .id(1L)
          .build();

        when(portfolioService.createNewEmptyPortfolio(any()))
          .thenReturn(mockPortfolio);

        mockMvc.perform(post(baseUrl))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.success", equalTo(true)))
          .andExpect(jsonPath("$.data.id", equalTo(1)))
          .andExpect(jsonPath("$.error", nullValue()));
    }

    @Test
    void findPortfolio() throws Exception {
        Portfolio mockPortfolio = Portfolio.builder()
          .id(1L)
          .build();

        when(portfolioService.getPortfolioWithSubData(any()))
          .thenReturn(mockPortfolio);

        mockMvc.perform(get(baseUrl+"/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.success", equalTo(true)))
          .andExpect(jsonPath("$.error", nullValue()))
          .andExpect(jsonPath("$.data").exists())
          .andExpect(jsonPath("$.data.id").exists())
          .andExpect(jsonPath("$.data").value(hasKey("user")))
          .andExpect(jsonPath("$.data").value(hasKey("basicProfile")))
          .andExpect(jsonPath("$.data").value(hasKey("careers")))
          .andExpect(jsonPath("$.data").value(hasKey("educations")))
          .andExpect(jsonPath("$.data").value(hasKey("licenses")))
          .andExpect(jsonPath("$.data").value(hasKey("projects")))
          .andExpect(jsonPath("$.data").value(hasKey("skills")))
          .andExpect(jsonPath("$.data").value(hasKey("public")));
    }
}