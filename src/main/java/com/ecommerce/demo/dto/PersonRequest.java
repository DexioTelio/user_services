package com.ecommerce.demo.dto;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PersonRequest {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateBirth;
    private final String email;
    private final String password;
    private final Gender gender;
    private final Role role;
    private final String profileImageUrl;
    private final Boolean termsAccepted;

    @JsonCreator
    public PersonRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("dateBirth") LocalDate dateBirth,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("role") Role role,
            @JsonProperty("profileImageUrl") String profileImageUrl,
            @JsonProperty("termsAccepted") Boolean termsAccepted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
        this.termsAccepted = termsAccepted;
    }
}
