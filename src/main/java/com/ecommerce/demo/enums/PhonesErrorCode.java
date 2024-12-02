package com.ecommerce.demo.enums;

public enum PhonesErrorCode {
  PHONES_CREATION_FAILURE("The phone/s already exists.");

  private final String message;

  PhonesErrorCode(String message) { this.message = message; }

  public String getMessage() { return message; }
}
