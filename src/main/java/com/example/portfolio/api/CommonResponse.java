package com.example.portfolio.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class CommonResponse {

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ResponseDto<T> {
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

  public static <T> ResponseDto<T> success(T data){
    return new ResponseDto<>(true, data, null);
  }

  public static ResponseDto<?> error(String message, int status){
    return new ResponseDto<>(false, null, new ErrorDto(message, status));
  }
}
