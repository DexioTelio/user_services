package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record RegistrationRequest(
        @NotBlank(message = "First name is required") @Size(max = 100, message = "First name cannot exceed 50 characters") String firstName,
        @NotBlank(message = "Last name is required") @Size(max = 100, message = "Last name cannot exceed 50 characters") String lastName,
        @NotBlank(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate dateBirth,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
        @NotBlank(message = "Password is required") @Size(min = 12, message = "Password must be at least 12 characters long") String password,
        @NotBlank(message = "Gender is required") Gender gender, @NotBlank(message = "Role is required") Role role,
        String profileImageUrl, @NotBlank(message = "Terms acceptance is required") Boolean termsAccepted,
        @NotNull(message = "Address is required") Set<AddressRequest> addresses,
        @NotNull(message = "Phones is required") Set<PhoneRequest> phones) {
}
