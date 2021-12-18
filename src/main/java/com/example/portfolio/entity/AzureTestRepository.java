package com.example.portfolio.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AzureTestRepository extends JpaRepository<AzureTest, Long> {
}
