package com.ecommerce.demo.model;

import com.ecommerce.demo.enums.PhoneType;

public class Phone {
  private final Long phoneId;
  private final Long personId;
  private final String phoneNumber;
  private final PhoneType phoneType;

  public Phone(Builder builder) {
    this.phoneId = builder.phoneId;
    this.personId = builder.personId;
    this.phoneNumber = builder.phoneNumber;
    this.phoneType = builder.phoneType;
  }

  public static class Builder {
    private Long phoneId;
    private Long personId;
    private String phoneNumber;
    private PhoneType phoneType;

    public Builder phoneId(Long phoneId) { this.phoneId = phoneId; return this; }
    public Builder personId(Long personId) { this.personId = personId; return this; }
    public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
    public Builder phoneType(PhoneType phoneType) { this.phoneType = phoneType; return this; }

    public Phone build() {
      return new Phone(this);
    }
  }
}
