package com.example.portfolio.domain.image;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
  @EntityGraph(attributePaths = {"user"})
  Image findByFileName(String fileName);
}
