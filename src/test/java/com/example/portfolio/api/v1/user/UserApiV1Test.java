package com.example.portfolio.api.v1.user;

import com.example.portfolio.api.v1.user.dto.UserJoinRequestDtoV1;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.security.TokenProvider;
import com.example.portfolio.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
    controllers = UserApiV1.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
    }
)
@AutoConfigureMockMvc(addFilters = false)
class UserApiV1Test {

  private final String baseUrl = "/api/v1/users";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userService;

  @MockBean
  private TokenProvider tokenProvider;

  private String name = "name";
  private String email = "aaa@bbb.com";
  private String password = "password";

  @Test
  @WithAnonymousUser
  void joinTest() throws Exception {
    UserJoinRequestDtoV1 bodyDto = new UserJoinRequestDtoV1();
    bodyDto.setEmail(email);
    bodyDto.setName(name);
    bodyDto.setPassword(password);

    User user = User.builder(name, email, password).id(1L).build();

    when(userService.join(name, email, password))
        .thenReturn(user);

    ResultActions action = mockMvc.perform(
        post(baseUrl+"/auth/join")
            .content(objectMapper.writeValueAsString(bodyDto))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .with(SecurityMockMvcRequestPostProcessors.csrf()));

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("success").value(true))
        .andExpect(jsonPath("data.id").value(1L))
        .andExpect(jsonPath("data.name").value(name))
        .andExpect(jsonPath("data.email").value(email))
        .andExpect(jsonPath("error").value(IsNull.nullValue()));

  }
}