package com.example.portfolio.service.basicProfile;

import com.example.portfolio.domain.basicprofile.BasicProfile;
import com.example.portfolio.domain.basicprofile.BasicProfileRepository;
import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.domain.portfolio.PortfolioRepository;
import com.example.portfolio.error.NotFoundException;
import com.example.portfolio.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BasicProfileService {
  private final BasicProfileRepository repository;
  private final PortfolioRepository portfolioRepository;

  public BasicProfile addBasicProfile(
    Long portfolioId, BasicProfile basicProfile, Image profileImage
  ){
    Portfolio portfolio = portfolioRepository.findById(portfolioId)
      .orElseThrow(()->new NotFoundException("포트폴리오가 존재하지 않습니다."));
    basicProfile.setProfileImage(profileImage);
    basicProfile.isAddedTo(portfolio);
    return repository.save(basicProfile);
  }
}
