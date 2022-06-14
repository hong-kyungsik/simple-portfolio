package com.example.portfolio.service.portfolio;

import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.domain.portfolio.PortfolioRepository;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.domain.user.UserRepository;
import com.example.portfolio.error.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService service;

    @Test
    @DisplayName("사용자가 새로운 빈 포트폴리오를 생성한다.")
    void createNewEmptyPortfolioTest() {
        //given
        User user = User.builder("name", "aaa@bbb.com", "password")
            .id(1L)
            .build();

        Portfolio portfolio = Portfolio.builder()
            .id(1L)
            .user(user)
            .build();

        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(portfolio);
        when(portfolioRepository.findById(any())).thenReturn(Optional.of(portfolio));

        //then
        Portfolio savedPortfolio = service.createNewEmptyPortfolio(1L);
        assertEquals(1L, savedPortfolio.getId());
    }

    @Test
    @DisplayName("존재하지 않는 사용자가 포트폴리오를 생성하면 예외가 발생한다.")
    void notFoundExceptionWillBeThrownWhenUserNotExists(){
        //given
        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThrows(NotFoundException.class, ()-> service.createNewEmptyPortfolio(1L));
    }

    @Test
    @DisplayName("포트폴리오 생성이 정상적으로 이루어지지 않으면 예외가 발생한다.")
    void exceptionWillBeThrownIfErrorOccurredWhenCreateNewPortfolio(){
        //given
        User user = User.builder("name", "aaa@bbb.com", "password")
            .id(1L)
            .build();

        Portfolio portfolio = Portfolio.builder()
            .id(1L)
            .user(user)
            .build();

        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(portfolio);
        when(portfolioRepository.findById(any())).thenReturn(Optional.empty());

        //then
        assertThrows(IllegalStateException.class, ()-> service.createNewEmptyPortfolio(1L));
    }
}