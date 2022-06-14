package com.example.portfolio.api;

import com.example.portfolio.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.stream.Collectors;

import static com.example.portfolio.api.CommonResponse.ErrorDto;
import static com.example.portfolio.api.CommonResponse.ResponseDto;

@RestControllerAdvice(basePackages = "com.example.portfolio.api")
@Slf4j
public class ApiExceptionHandler {

  public ResponseDto<?> createNewErrorResult(String message, HttpStatus status){
    return new ResponseDto<>(false, null, new ErrorDto(message, status));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<?> handleNotFoundException(NotFoundException e){
    log.error("not found");
    return createNewErrorResult(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
      IllegalArgumentException.class,
      IllegalStateException.class,
      MethodArgumentNotValidException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<?> handleBadRequestException(Exception e){
    log.error("Bad request", e);
    return createNewErrorResult(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      ConstraintViolationException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<?> handleConstraintViolationException(ConstraintViolationException e){
    log.error("Bad request", e);
    return createNewErrorResult(
        e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({Exception.class, RuntimeException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<?> handleException(Exception e){
    log.error("error occurred", e);
    return createNewErrorResult(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
