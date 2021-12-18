package com.example.portfolio.controller;

import com.example.portfolio.entity.AzureTest;
import com.example.portfolio.entity.AzureTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class AzureTestController {

  private final AzureTestRepository azureTestRepository;

  @PostMapping("/azure/tests/new")
  public ResponseEntity<String> insert(@RequestBody TestDto dto){
    AzureTest test = new AzureTest();
    test.setContent(dto.getContent());

    azureTestRepository.save(test);
    return ResponseEntity.ok(test.toString());
  }

  @GetMapping("/azure/tests")
  public ResponseEntity<String> selectAll(){
    List<AzureTest> tests = azureTestRepository.findAll();

    return ResponseEntity.ok(tests.stream()
        .map(AzureTest::toString)
        .collect(Collectors.joining()));
  }
}
