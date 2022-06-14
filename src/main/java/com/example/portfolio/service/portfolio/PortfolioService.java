package com.example.portfolio.service.portfolio;

import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.domain.portfolio.PortfolioRepository;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.domain.user.UserRepository;
import com.example.portfolio.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final UserRepository userRepository;
    private final PortfolioRepository repository;

    @Transactional
    public Portfolio createNewEmptyPortfolio(Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(()->new NotFoundException("user not exists."));

        Portfolio portfolio = Portfolio.builder()
            .user(user)
            .build();

        repository.save(portfolio);

        return repository.findById(portfolio.getId())
            .orElseThrow(()->
                new IllegalStateException("error occurred during create new portfolio"));
    }
}
