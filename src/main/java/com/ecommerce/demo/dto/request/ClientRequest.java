package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.PreferredPaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.ZonedDateTime;

public record ClientRequest(
        @Positive(message = "Person ID must be a positive value")
        @JsonProperty("person_id") Long personId,

        @Min(value = 0, message = "The loyalty points cannot be negative")
        @JsonProperty("loyalty_points") Integer loyaltyPoints,

        @NotNull(message = "Preferred payment method is required")
        @JsonProperty("Preferred_payment_method") PreferredPaymentMethod preferredPaymentMethod,

        @NotNull(message = "Customer segment is required")
        @JsonProperty("customer_segment") CustomerSegment customerSegment,

        @JsonProperty("last_purchase_date") ZonedDateTime lastPurchaseDate
) {
  public ClientRequest {
    if (loyaltyPoints == null) {
      loyaltyPoints = 0;
    }
    if (preferredPaymentMethod == null) {
      preferredPaymentMethod = PreferredPaymentMethod.CREDIT_CARD;
    }
    if (customerSegment == null) {
      customerSegment = CustomerSegment.NEW;
    }
  }
}

