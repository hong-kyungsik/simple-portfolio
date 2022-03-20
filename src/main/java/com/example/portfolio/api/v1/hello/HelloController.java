package com.example.portfolio.api.v1.hello;

import com.example.portfolio.api.CommonResponse.ResultDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.portfolio.api.CommonResponse.success;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping
    public ResultDto<String> hello(
        @AuthenticationPrincipal Long userId
    ){
        return success("hello user"+userId.toString());
    }
}
