package com.ecommerce.demo.enums;

public enum ClientErrorCode {
  CLIENT_ALREADY_EXISTS("The client already exists.");

  private final String message;

  ClientErrorCode(String message) {this.message = message; }

  public String getMessage() {
    return message;
  }
}
