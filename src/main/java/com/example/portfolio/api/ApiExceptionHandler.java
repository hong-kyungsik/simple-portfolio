package com.example.portfolio.api;

import com.example.portfolio.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static com.example.portfolio.api.CommonResponse.ErrorDto;
import static com.example.portfolio.api.CommonResponse.ResultDto;

@RestControllerAdvice(basePackages = "com.example.portfolio.api")
@Slf4j
public class ApiExceptionHandler {

  public ResultDto<?> createNewErrorResult(String message, HttpStatus status){
    return new ResultDto<>(false, null, new ErrorDto(message, status));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResultDto<?> NotFoundExceptionHandler(NotFoundException e){
    log.error("not found");
    return createNewErrorResult(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
      IllegalArgumentException.class,
      IllegalStateException.class,
      ConstraintViolationException.class,
      MethodArgumentNotValidException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResultDto<?> BadRequestExceptionHandler(Exception e){
    log.error("Bad request", e);
    return createNewErrorResult(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({Exception.class, RuntimeException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResultDto<?> ExceptionHandler(Exception e){
    log.error("error occurred", e);
    return createNewErrorResult(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
