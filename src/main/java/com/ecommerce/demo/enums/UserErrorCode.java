package com.ecommerce.demo.enums;

public enum UserErrorCode {
    USER_ALREADY_EXISTS("The user already exists."),
    USER_CREATION_FAILURE("Failed to create user."),
    USER_AGE_FAILURE("Age must be greater than 18."),
    USER_USERNAME_EXISTS("The username already exists."),
    USER_FIRSTNAME_LENGTH_FAILURE("First name must be between 3 and 50 characters."),
    USER_LASTNAME_LENGTH_FAILURE("Last name must be between 3 and 50 characters."),
    USER_USERNAME_LENGTH_FAILURE("User name must be between 3 and 50 characters."),
    USER_PASSWORD_COMPLEXITY_FAILURE("The password must be at least 14 characters long, " +
            "contain at least one lower case letter, one upper case letter, one number and one special character."),
    USER_GENDER_FAILURE("Gender must be 'male', 'female', or 'other'."),
    USER_ROLE_FAILURE("Role must be 'client.sql', 'admin', or 'visitor'."),
    USER_PHONES_FAILURE("At least one phone number is required. You must provide between 1 and 2 phone numbers."),
    USER_EMAIL_FORMAT_FAILURE("Email format is invalid."),
    USER_PHONE_FORMAT_FAILURE("Phone number format is invalid. It must start with '+' " +
            "followed by 1 to 3 digits for the country code, and then 4 to 14 digits for the phone number: " +
            "[\"+11234567890\"]"),
    USER_ADDRESS_EMPTY("Address cannot be empty."),
    USER_ADDRESS_CREATION_FAILURE("User created, but address creation failed. User has been removed. Errors: %s"),
    USER_PHONE_EMPTY("phone cannot be empty.");

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}