package com.example.portfolio.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class CommonResponse {

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ResultDto<T> {
    private boolean success;
    private T data;
    private ErrorDto error;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ErrorDto {
    private String message;
    private int status;

    public ErrorDto(String message, HttpStatus status){
      this.message = message;
      this.status = status.value();
    }
  }

  public static <T> ResultDto<T> success(T data){
    return new ResultDto<>(true, data, null);
  }

  public static ResultDto<?> error(String message, int status){
    return new ResultDto<>(false, null, new ErrorDto(message, status));
  }
}