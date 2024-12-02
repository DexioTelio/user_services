package com.ecommerce.demo.model;

import com.ecommerce.demo.enums.CustomerSegment;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.PreferredPaymentMethod;
import com.ecommerce.demo.enums.Role;

import java.time.ZonedDateTime;

import java.util.Set;

public class Client {
    private final Long id;
    private final Long personId;
    private final int loyaltyPoints;
    private final PreferredPaymentMethod preferredPaymentMethod;
    private final CustomerSegment customerSegment;
    private final ZonedDateTime lastPurchaseDate;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public Client(Builder builder) {
        this.id = builder.id;
        this.personId = builder.personId;
        this.loyaltyPoints = builder.loyaltyPoints;
        this.preferredPaymentMethod = builder.preferredPaymentMethod;
        this.customerSegment = builder.customerSegment;
        this.lastPurchaseDate = builder.lastPurchaseDate;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private Long id;
        private Long personId;
        private int loyaltyPoints;
        private PreferredPaymentMethod preferredPaymentMethod;
        private CustomerSegment customerSegment;
        private ZonedDateTime lastPurchaseDate;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder personId(Long personId) { this.personId = personId; return this; }
        public Builder loyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; return this; }
        public Builder preferredPaymentMethod(PreferredPaymentMethod preferredPaymentMethod) { this.preferredPaymentMethod = preferredPaymentMethod; return this; }
        public Builder customerSegment(CustomerSegment customerSegment) { this.customerSegment = customerSegment; return this; }
        public Builder lastPurchaseDate(ZonedDateTime lastPurchaseDate) { this.lastPurchaseDate = lastPurchaseDate; return this; }

        public Client build() {
            return new Client(this);
        }
    }

    public Long getId() { return id; }
    public Long getPersonId() { return personId; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public PreferredPaymentMethod getPreferredPaymentMethod() { return preferredPaymentMethod; }
    public CustomerSegment getCustomerSegment() { return customerSegment; }
    public ZonedDateTime getLastPurchaseDate() { return lastPurchaseDate; }
    public ZonedDateTime getCreatedAt() { return createdAt; }
    public ZonedDateTime getUpdatedAt() { return updatedAt; }
}
