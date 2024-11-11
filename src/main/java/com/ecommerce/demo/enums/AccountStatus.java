package com.ecommerce.demo.enums;

public enum AccountStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    SUSPENDED("suspended"),
    PENDING("pending"),
    CLOSED("closed"),
    BANNED("banned");

    private final String value;

    AccountStatus(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
