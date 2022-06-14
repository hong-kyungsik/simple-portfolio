package com.example.portfolio.service.user;

import com.example.portfolio.domain.user.User;
import com.example.portfolio.domain.user.UserRepository;
import com.example.portfolio.error.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입이 성공한다.")
    void joinTest(){
        User user = User.builder("","","").id(1L).build();
        Optional<User> optUser = Optional.of(user);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findById(any())).thenReturn(optUser);

        User result = userService.join(user.getName(), user.getEmail(), user.getPassword());
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("회원가입시 이메일이 이미 존재하면 예외가 발생한다.")
    void emailDuplicationTest(){
        //given
        User alreadyExists = User.builder("", "", "")
            .id(1L).build();

        //when
        when(userRepository.findByEmail(anyString()))
            .thenReturn(Optional.of(alreadyExists));

        //then
        assertThrows(IllegalStateException.class, ()-> userService.join("", "", ""));
    }

    @Test
    @DisplayName("로그인이 정상적으로 성공한다.")
    void loginTest(){
        //given
        String email = "aaa@bbb.com";
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = User.builder("name", email, passwordEncoder.encode(password))
            .build();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        //then
        User loggedInUser = userService.login(email, password, passwordEncoder);
        assertEquals(email, loggedInUser.getEmail());
    }

    @Test
    @DisplayName("로그인시 사용자가 없으면 예외가 발생한다.")
    void notFoundExceptionOccurredIfUserNotExistsAtLogin(){
        //given
        String email = "aaa@bbb.com";
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //when
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        //then
        assertThrows(NotFoundException.class, ()-> userService.login(email, password, passwordEncoder));
    }

    @Test
    @DisplayName("로그인시 아이디, 비밀번호가 일치하지 않으면 예외가 발생한다.")
    void exceptionOccurredIfEmailAndPasswordNotMatch(){
        //given
        String email = "aaa@bbb.com";
        String password = "password";
        String wrongPassword = "wrongPassword";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = User.builder("name", email, password)
            .build();
        user.encodePassword();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        //then
        assertThrows(IllegalStateException.class, ()-> userService.login(email, wrongPassword, passwordEncoder));
    }
}