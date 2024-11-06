package com.ecommerce.demo.enums;

public enum PhoneType {
    MOBILE("mobile"),
    FIXED("fixed"),
    WORK("work"),
    EMERGENCY("emergency");

    private final String value;

    PhoneType(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
