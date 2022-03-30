package com.example.portfolio.service.user;

import com.example.portfolio.domain.user.User;
import com.example.portfolio.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void joinTest(){
    User user = User.builder("","","").id(1L).build();
    Optional<User> optUser = Optional.of(user);

    when(userRepository.save(any())).thenReturn(user);
    when(userRepository.findById(any())).thenReturn(optUser);

    User result = userService.join(user.getName(), user.getEmail(), user.getPassword());
    assertEquals(1L, result.getId());
  }
}