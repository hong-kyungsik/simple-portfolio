package com.example.portfolio.api.v1.portfolio.dto;

import com.example.portfolio.domain.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewPortfolioResponseDtoV1 {
    private Long id;

    public static AddNewPortfolioResponseDtoV1 fromPortfolio(
        Portfolio portfolio
    ){
        AddNewPortfolioResponseDtoV1 dto = new AddNewPortfolioResponseDtoV1();
        dto.id = portfolio.getId();
        return dto;
    }
}
