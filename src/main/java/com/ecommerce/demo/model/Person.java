package com.ecommerce.demo.model;

import com.ecommerce.demo.enums.AccountStatus;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
    private String profileImageUrl;
    private LocalDateTime lastLogin;
    private String communicationPreference;
    private boolean termsAccepted;
    private AccountStatus accountStatus;
    private boolean emailVerified;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Person(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.email = builder.email;
        this.password = builder.password;
        this.gender = builder.gender;
        this.role = builder.role;
        this.profileImageUrl = builder.profileImageUrl;
        this.lastLogin = builder.lastLogin;
        this.communicationPreference = builder.communicationPreference;
        this.termsAccepted = builder.termsAccepted;
        this.accountStatus = builder.accountStatus;
        this.emailVerified = builder.emailVerified;
        this.bio = builder.bio;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String email;
        private String password;
        private Gender gender;
        private Role role;
        private String profileImageUrl;
        private LocalDateTime lastLogin;
        private String communicationPreference;
        private boolean termsAccepted;
        private AccountStatus accountStatus;
        private boolean emailVerified;
        private String bio;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setGender(Gender gender) { this.gender = gender; return this; }
        public Builder setRole(Role role) { this.role = role; return this; }
        public Builder setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; return this; }
        public Builder setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; return this; }
        public Builder setCommunicationPreference(String communicationPreference) { this.communicationPreference = communicationPreference; return this; }
        public Builder setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; return this; }
        public Builder setAccountStatus(AccountStatus accountStatus) { this.accountStatus = accountStatus; return this; }
        public Builder setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; return this; }
        public Builder setBio(String bio) { this.bio = bio; return this; }
        public Builder setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Person build() {
            return new Person(this);
        }
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Gender getGender() { return gender; }
    public Role getRole() { return role; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public String getCommunicationPreference() { return communicationPreference; }
    public boolean isTermsAccepted() { return termsAccepted; }
    public AccountStatus getAccountStatus() { return accountStatus; }
    public boolean isEmailVerified() { return emailVerified; }
    public String getBio() { return bio; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
