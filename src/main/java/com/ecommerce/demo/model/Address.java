package com.ecommerce.demo.model;

import java.time.ZonedDateTime;

public class Address {
    private final Long personId;
    private final String street;
    private final String streetNumber;
    private final String apartmentNumber;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public Address(Builder builder) {
        this.personId = builder.personId;
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.apartmentNumber = builder.apartmentNumber;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public static class Builder {
        private Long personId;
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

        public Builder personId(Long personId) { this.personId = personId; return this; }
        public Builder street(String street) { this.street = street; return this; }
        public Builder streetNumber(String streetNumber) { this.streetNumber = streetNumber; return this; }
        public Builder apartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; return this; }
        public Builder neighborhood(String neighborhood) { this.neighborhood = neighborhood; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder state(String state) { this.state = state; return this; }
        public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        public Builder country(String country) { this.country = country; return this; }

        public Address build() {
            return new Address(this);
        }
    }

    public Long getPersonId() {
        return personId;
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
}
