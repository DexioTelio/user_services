package com.ecommerce.demo.dto.request.update;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.PreferredPaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

import java.time.ZonedDateTime;

public record ClientUpdate(
        @Positive(message = "Person ID must be a positive value")
        @JsonProperty("person_id") Long personId,

        @JsonProperty("Preferred_payment_method") PreferredPaymentMethod preferredPaymentMethod,

        @JsonProperty("customer_segment") CustomerSegment customerSegment,

        @JsonProperty("last_purchase_date") ZonedDateTime lastPurchaseDate
) {
}
