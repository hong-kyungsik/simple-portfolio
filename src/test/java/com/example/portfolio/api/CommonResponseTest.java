package com.example.portfolio.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonResponseTest {

  public static class TestObject{
    private final String testAttributeString = "testString";
    private final Integer testAttributeInteger = 3;
  }

  @Test
  void success() {
    TestObject object = new TestObject();
    CommonResponse.ResponseDto<TestObject> result = CommonResponse.success(object);

    assertTrue(result.isSuccess());
    assertNotNull(result.getData());
    assertEquals(object.testAttributeString, result.getData().testAttributeString);
    assertEquals(object.testAttributeInteger, result.getData().testAttributeInteger);
    assertNull(result.getError());
  }

  @Test
  void error() {
    String message = "test message";
    int status = 56;
    CommonResponse.ResponseDto<?> result = CommonResponse.error(message, status);

    assertFalse(result.isSuccess());
    assertNull(result.getData());
    assertEquals(message, result.getError().getMessage());
    assertEquals(status, result.getError().getStatus());
  }
}