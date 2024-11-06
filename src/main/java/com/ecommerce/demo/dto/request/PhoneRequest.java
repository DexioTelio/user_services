package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneRequest {
    private final Long id;
    private final Long personId;
    private final String phoneNumber;
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
