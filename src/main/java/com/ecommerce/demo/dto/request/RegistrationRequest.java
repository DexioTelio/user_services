package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.dto.validation.annotations.MinAge;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record RegistrationRequest(
        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 100, message = "First name must be between 3 and 100 characters")
        @JsonProperty("first_name") String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters")
        @JsonProperty("last_name") String lastName,

        @NotNull(message = "Date of birth is required")
        @MinAge(value = 18, message = "You must be at least 18 years old")
        @JsonProperty("date_birth") LocalDate dateBirth,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @JsonProperty("email") String email,

        @NotBlank(message = "Password is required")
        @Size(min = 12, message = "Password must be at least 12 characters long")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$",
                message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
        @JsonProperty("password") String password,

        @NotBlank(message = "Gender is required")
        @JsonProperty("gender") Gender gender,

        @JsonProperty("profile_image_url") String profileImageUrl,

        @NotBlank(message = "Terms acceptance is required")
        @JsonProperty("terms_accepted") Boolean termsAccepted,

        @NotEmpty(message = "Address is required")
        @JsonProperty("addresses") Set<AddressRequest> addresses,

        @NotEmpty(message = "Phones is required")
        @JsonProperty("phones") Set<PhoneRequest> phones
) {}
