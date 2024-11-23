package com.ecommerce.demo.dto.response;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.PreferredPaymentMethod;

import java.time.ZonedDateTime;

public class ClientCreatedResponse {
  private final Long id;
  private final Long personId;
  private final int loyaltyPoints;
  private final PreferredPaymentMethod preferredPaymentMethod;
  private final CustomerSegment customerSegment;
  private final ZonedDateTime lastPurchaseDate;

  public ClientCreatedResponse(Builder builder) {
    this.id = builder.id;
    this.personId = builder.personId;
    this.loyaltyPoints = builder.loyaltyPoints;
    this.preferredPaymentMethod = builder.preferredPaymentMethod;
    this.customerSegment = builder.customerSegment;
    this.lastPurchaseDate = builder.lastPurchaseDate;
  }

  public static class Builder {
    private Long id;
    private Long personId;
    private int loyaltyPoints;
    private PreferredPaymentMethod preferredPaymentMethod;
    private CustomerSegment customerSegment;
    private ZonedDateTime lastPurchaseDate;

    public Builder id(Long id) { this.id = id; return this; }
    public Builder personId(Long personId) { this.personId = personId; return this; }
    public Builder loyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; return this; }
    public Builder preferredPaymentMethod(PreferredPaymentMethod preferredPaymentMethod) { this.preferredPaymentMethod = preferredPaymentMethod; return this; }
    public Builder customerSegment(CustomerSegment customerSegment) { this.customerSegment = customerSegment; return this; }
    public Builder lastPurchaseDate(ZonedDateTime lastPurchaseDate) { this.lastPurchaseDate = lastPurchaseDate; return this; }
  }
}
