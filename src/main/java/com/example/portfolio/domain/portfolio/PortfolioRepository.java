package com.example.portfolio.domain.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  @Query("select p from Portfolio p join fetch BasicProfile b")
  Optional<Portfolio> findByIdWithSubData(Long id);
}
