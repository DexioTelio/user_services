package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.PreferredPaymentMethod;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class ClientRequest {
    private final Long personId;
    private final int loyaltyPoints;
    private final PreferredPaymentMethod preferredPaymentMethod;
    private final CustomerSegment customerSegment;
    private final ZonedDateTime lastPurchaseDate;

    @JsonCreator
    public ClientRequest(
            @JsonProperty("personId") Long personId,
            @JsonProperty("loyaltyPoints") int loyaltyPoints,
            @JsonProperty("preferredPaymentMethod") PreferredPaymentMethod preferredPaymentMethod,
            @JsonProperty("customerSegment") CustomerSegment customerSegment,
            @JsonProperty("lastPurchaseDate") ZonedDateTime lastPurchaseDate) {
        this.personId = personId;
        this.loyaltyPoints = loyaltyPoints;
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.customerSegment = customerSegment;
        this.lastPurchaseDate = lastPurchaseDate;
    }

}
