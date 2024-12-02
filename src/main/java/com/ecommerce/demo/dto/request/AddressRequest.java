package com.ecommerce.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record AddressRequest(
        @NotBlank(message = "Street is required")
        @Size(max = 100, message = "Street cannot exceed 100 characters")
        @JsonProperty() String street,

        @NotBlank(message = "Street number is required")
        @Size(max = 10, message = "Street number cannot exceed 10 characters")
        @JsonProperty() String streetNumber,

        @Size(max = 10, message = "Apartment number cannot exceed 10 characters")
        @JsonProperty() String apartmentNumber,

        @NotBlank(message = "Neighborhood is required")
        @Size(max = 50, message = "Neighborhood cannot exceed 50 characters")
        @JsonProperty() String neighborhood,

        @NotBlank(message = "City is required")
        @Size(max = 50, message = "City cannot exceed 50 characters")
        @JsonProperty() String city,

        @NotBlank(message = "State is required")
        @Size(max = 50, message = "State cannot exceed 50 characters")
        @JsonProperty() String state,

        @NotBlank(message = "Postal code is required")
        @Size(max = 20, message = "Postal code cannot exceed 20 characters")
        @JsonProperty() String postalCode,

        @NotBlank(message = "Country is required")
        @Size(max = 50, message = "Country cannot exceed 50 characters")
        @JsonProperty() String country
) {}
