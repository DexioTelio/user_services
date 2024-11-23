package com.ecommerce.demo.enums;

public enum ClientErrorCode {
  CLIENT_ALREADY_EXISTS("The client already exists."),
  CLIENT_CREATION_FAILURE("Failed to create client.");

  private final String message;

  ClientErrorCode(String message) {this.message = message; }

  public String getMessage() {
    return message;
  }
}
