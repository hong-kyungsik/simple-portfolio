package com.example.portfolio.api.v1.image;

import com.example.portfolio.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Slf4j
public class ImageApiV1 {
  private final StorageService service;

  @GetMapping(value = "/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
  @ResponseBody
  public ResponseEntity<Resource> getImage(@PathVariable String fileName){
    Resource image = service.loadAsResource(fileName);
    return new ResponseEntity<>(image, new HttpHeaders(), HttpStatus.OK);
  }
}
