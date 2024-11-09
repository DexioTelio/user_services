package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record RegistrationRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name cannot exceed 50 characters")
        @JsonProperty("first_name") String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name cannot exceed 50 characters")
        @JsonProperty("last_name") String lastName,

        @NotBlank(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        @JsonProperty("date_birth") LocalDate dateBirth,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @JsonProperty("email") String email,

        @NotBlank(message = "Password is required")
        @Size(min = 12, message = "Password must be at least 12 characters long")
        @JsonProperty("password") String password,

        @NotBlank(message = "Gender is required")
        @JsonProperty("gender") Gender gender,

        @NotBlank(message = "Role is required")
        @JsonProperty("role") Role role,

        @JsonProperty("profile_image_url") String profileImageUrl,

        @NotBlank(message = "Terms acceptance is required")
        @JsonProperty("terms_accepted") Boolean termsAccepted,

        @NotNull(message = "Address is required")
        @JsonProperty("addresses") Set<AddressRequest> addresses,

        @NotNull(message = "Phones is required")
        @JsonProperty("phones") Set<PhoneRequest> phones
) {}
