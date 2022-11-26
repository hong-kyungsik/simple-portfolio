package com.example.portfolio.api.v1.basicProfile;

import com.example.portfolio.api.CommonResponse.ResponseDto;
import com.example.portfolio.api.v1.basicProfile.dto.BasicProfileDtoV1;
import com.example.portfolio.domain.basicprofile.BasicProfile;
import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.service.basicProfile.BasicProfileService;
import com.example.portfolio.service.image.ImageService;
import com.example.portfolio.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.example.portfolio.api.CommonResponse.success;

@RestController
@RequestMapping("/api/v1/basic-profiles")
@RequiredArgsConstructor
@Slf4j
public class BasicProfileApiV1 {
  private final BasicProfileService service;
  private final ImageService imageService;
  private final UserService userService;

  @PostMapping("")
  public ResponseDto<BasicProfileDtoV1> addNewBasicProfileToPortfolio(
    @RequestPart(name="basicProfile") @Valid BasicProfileDtoV1 requestedBasicProfile,
    @RequestPart(name="profileImage") MultipartFile requestedProfileImage,
    @AuthenticationPrincipal Long userId
  ){
    User user = userService.getUserByUserId(userId);
    Image profileImage = imageService.storeImage(requestedProfileImage, user);

    BasicProfile basicProfile = service
      .addBasicProfile(
        requestedBasicProfile.getPortfolioId(),
        requestedBasicProfile.toBasicProfile(),
        profileImage);

    return success(BasicProfileDtoV1.fromBasicProfile(basicProfile));
  }
}
