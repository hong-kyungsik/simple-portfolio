package com.example.portfolio.service.image;

import com.example.portfolio.domain.image.Image;
import com.example.portfolio.domain.image.ImageRepository;
import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.domain.portfolio.PortfolioRepository;
import com.example.portfolio.domain.user.User;
import com.example.portfolio.error.NotFoundException;
import com.example.portfolio.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ImageService {
  private final StorageService storageService;
  private final ImageRepository repository;

  @Transactional
  public Image storeImage(MultipartFile file, User user){

    String storedFileName = storageService.store(file);

    Image image = Image.builder(
      storedFileName, file.getOriginalFilename(), user)
      .build();

    return repository.save(image);
  }

  @Transactional(readOnly = true)
  public Image getImage(String fileName){
    return repository.findByFileName(fileName);
  }
}
