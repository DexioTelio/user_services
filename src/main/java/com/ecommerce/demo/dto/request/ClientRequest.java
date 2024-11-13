package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.PreferredPaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.ZonedDateTime;

public record ClientRequest(
        @NotNull(message = "Id is required")
        @JsonProperty("id") Long id,

        @NotNull(message = "Person id is required")
        @JsonProperty("person_id") Long personId,

        @NotNull(message = "Loyalty points is required")
        @Min(value = 0, message = "The loyalty points cannot be negative")
        @JsonProperty("loyalty_points") int loyaltyPoints,

        @NotNull(message = "Preferred payment method is required")
        @JsonProperty("Preferred_payment_method") PreferredPaymentMethod preferredPaymentMethod,

        @NotNull(message = "Customer segment is required")
        @JsonProperty("customer_segment") CustomerSegment customerSegment,

        @NotNull(message = "Last purchase date is required")
        @PastOrPresent(message = "Last purchase date must e a past or present date")
        @JsonProperty("last_purchase_date") ZonedDateTime lastPurchaseDate
) {}

