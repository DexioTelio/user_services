package com.ecommerce.demo.dto.response;

import com.ecommerce.demo.enums.Gender;

import java.time.ZonedDateTime;
import java.util.Set;

public class PersonResponse {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Set<String> phones;
    private final Gender gender;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    private PersonResponse(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phones = builder.phones;
        this.gender = builder.gender;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private Set<String> phones;
        private Gender gender;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phones(Set<String> phones) { this.phones = phones; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }
        public Builder createdAt(ZonedDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(ZonedDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public PersonResponse build() {
            return new PersonResponse(this);
        }
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Set<String> getPhones() { return phones; }
    public Gender getGender() { return gender; }
    public ZonedDateTime getCreatedAt() { return createdAt; }
    public ZonedDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", gender=" + gender +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
