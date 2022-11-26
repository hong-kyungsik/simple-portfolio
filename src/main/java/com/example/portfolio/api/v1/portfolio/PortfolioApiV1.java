package com.example.portfolio.api.v1.portfolio;

import com.example.portfolio.api.CommonResponse.ResponseDto;
import com.example.portfolio.api.v1.portfolio.dto.AddNewPortfolioResponseDtoV1;
import com.example.portfolio.api.v1.portfolio.dto.PortfolioDtoV1;
import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.portfolio.api.CommonResponse.success;

@RestController
@RequestMapping("/api/v1/portfolios")
@RequiredArgsConstructor
@Slf4j
public class PortfolioApiV1 {
    private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseDto<PortfolioDtoV1> findPortfolio(
        @PathVariable Long id
    ){
        Portfolio portfolio = portfolioService.getPortfolioWithSubData(id);

        PortfolioDtoV1 response =
            PortfolioDtoV1.fromPortfolio(portfolio);

        return success(response);
    }

    @PostMapping("")
    public ResponseDto<AddNewPortfolioResponseDtoV1> addNewPortfolio(
        @AuthenticationPrincipal Long userId
    ){
        Portfolio portfolio = portfolioService.createNewEmptyPortfolio(userId);

        AddNewPortfolioResponseDtoV1 response =
            AddNewPortfolioResponseDtoV1.fromPortfolio(portfolio);

        return success(response);
    }

}
