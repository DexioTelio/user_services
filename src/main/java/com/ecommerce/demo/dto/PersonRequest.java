package com.ecommerce.demo.dto;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PersonRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 50 characters")
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 50 characters")
    private final String lastName;

    @NotBlank(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private final LocalDate dateBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 12, message = "Password must be at least 12 characters long")
    private final String password;

    @NotBlank(message = "Gender is required")
    private final Gender gender;

    @NotBlank(message = "Role is required")
    private final Role role;

    private final String profileImageUrl;

    @NotBlank(message = "Terms acceptance is required")
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
