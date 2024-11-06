package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PhoneRequest {
    @NotNull(message = "ID is required")
    private final Long id;

    @NotNull(message = "Person ID is required")
    private final Long personId;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{1,4}?\\)?)[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$",
            message = "Phone number format is invalid")
    private final String phoneNumber;

    @NotNull(message = "Phone type is required")
    private final PhoneType phoneType;

    @JsonCreator
    public PhoneRequest(
            @JsonProperty("id") Long id,
            @JsonProperty("personId") Long personId,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("phoneType") PhoneType phoneType
    ) {
        this.id = id;
        this.personId = personId;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }
}
