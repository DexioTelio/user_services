package com.ecommerce.demo.model;

import java.time.ZonedDateTime;

public class Address {
    private final Long userId;
    private final String street;
    private final String streetNumber;
    private final String apartmentNumber;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public Address(Builder builder) {
        this.userId = builder.userId;
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.apartmentNumber = builder.apartmentNumber;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private Long userId;
        private String street;
        private String streetNumber;
        private String apartmentNumber;
        private String neighborhood;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder userId(Long userId) { this.userId = userId; return this; }
        private Builder street(String street) { this.street = street; return this; }
        private Builder streetNumber(String streetNumber) { this.streetNumber = streetNumber; return this; }
        private Builder apartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; return this; }
        private Builder neighborhood(String neighborhood) { this.neighborhood = neighborhood; return this; }
        private Builder city(String city) { this.city = city; return this; }
        private Builder state(String state) { this.state = state; return this; }
        private Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        private Builder country(String country) { this.country = country; return this; }

        public Address build() {
            return new Address(this);
        }
    }

    public Long getUserId() {
        return userId;
    }
    public String getStreet() {
        return street;
    }
    public String getStreetNumber() {
        return streetNumber;
    }
    public String getApartmentNumber() {
        return apartmentNumber;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getCountry() { return country; }
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
