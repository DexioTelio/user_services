package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PhoneRequest(
    @NotNull(message = "ID is required")
    @JsonProperty("id") Long id,

    @NotNull(message = "Person ID is required")
    @JsonProperty("person_id") Long personId,

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{1,4}?\\)?)[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$",
            message = "Phone number format is invalid")
    @JsonProperty("phone_number") String phoneNumber,

    @NotNull(message = "Phone type is required")
    @JsonProperty("phone_type") PhoneType phoneType
) {}
