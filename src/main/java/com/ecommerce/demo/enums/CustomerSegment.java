package com.ecommerce.demo.enums;

public enum CustomerSegment {
    VIP("vip"),
    FREQUENT("frequent"),
    OCCASIONAL("occasional"),
    NEW("new");

    private final String value;

    CustomerSegment(String value) {this.value = value; }

    public String getValue() {
        return value;
    }
}
