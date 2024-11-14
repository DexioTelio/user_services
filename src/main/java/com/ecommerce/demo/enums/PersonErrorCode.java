package com.ecommerce.demo.enums;

public enum PersonErrorCode {
    PERSON_ALREADY_EXISTS("The person already exists."),
    PERSON_CREATION_FAILURE("Failed to create person."),
    PERSON_AGE_FAILURE("Age must be greater than 18."),
    PERSON_FIRSTNAME_LENGTH_FAILURE("First name must be between 3 and 50 characters."),
    PERSON_LASTNAME_LENGTH_FAILURE("Last name must be between 3 and 50 characters."),
    PERSON_PASSWORD_COMPLEXITY_FAILURE("The password must be at least 14 characters long, " +
            "contain at least one lower case letter, one upper case letter, one number and one special character."),
    PERSON_GENDER_FAILURE("Gender must be 'male', 'female', or 'other'."),
    PERSON_ROLE_FAILURE("Role must be 'client.sql', 'admin', or 'visitor'."),
    PERSON_PHONES_FAILURE("At least one phone number is required. You must provide between 1 and 2 phone numbers."),
    PERSON_EMAIL_FORMAT_FAILURE("Email format is invalid."),
    PERSON_PHONE_FORMAT_FAILURE("Phone number format is invalid. It must start with '+' " +
            "followed by 1 to 3 digits for the country code, and then 4 to 14 digits for the phone number: " +
            "[\"+11234567890\"]"),
    PERSON_ADDRESS_EMPTY("Address cannot be empty."),
    PERSON_ADDRESS_CREATION_FAILURE("User created, but address creation failed. User has been removed. Errors: %s"),
    PERSON_PHONE_EMPTY("phone cannot be empty.");

    private final String message;

    PersonErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}