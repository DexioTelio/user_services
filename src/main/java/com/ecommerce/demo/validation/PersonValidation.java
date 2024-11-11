package com.ecommerce.demo.validation;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.PhoneRequest;
import com.ecommerce.demo.dto.request.RegistrationRequest;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.ecommerce.demo.enums.UserErrorCode;
import com.ecommerce.demo.util.Dictionary;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.function.Predicate;

public class PersonValidation {
    // Private constructor to prevent instantiation
    private PersonValidation() {}

    // Method to validate a UserRequest object
    public static Either<Set<String>, RegistrationRequest> validateUserRequest(RegistrationRequest request) {
        Dictionary<RegistrationRequest> validation = new Dictionary<>(); // Create a new Dictionary for validation rules

        // Add validation rules for first name length
        validation.addValidationRule(
                req -> isLengthValid(request.firstName(), 3, 50),
                UserErrorCode.USER_FIRSTNAME_LENGTH_FAILURE.getMessage()
        );
        // Add validation rules for last name length
        validation.addValidationRule(
                req -> isLengthValid(request.lastName(), 3, 50),
                UserErrorCode.USER_LASTNAME_LENGTH_FAILURE.getMessage()
        );
        // Add validation rules for age
        validation.addValidationRule(
                req -> validateAge(request.dateBirth()),
                UserErrorCode.USER_AGE_FAILURE.getMessage()
        );
        // Add validation rules for email format
        validation.addValidationRule(
                req -> validateEmail(request.email()),
                UserErrorCode.USER_EMAIL_FORMAT_FAILURE.getMessage()
        );
        // Add validation rules for password
        validation.addValidationRule(
                req -> isPasswordValid(request.password(), 14),
                UserErrorCode.USER_PASSWORD_COMPLEXITY_FAILURE.getMessage()
        );
        // Add validation rules for gender
        validation.addValidationRule(
                req -> validateContentGender(request.gender()),
                UserErrorCode.USER_GENDER_FAILURE.getMessage()
        );
        // Add validation rules for address
        validation.addValidationRule(
                req -> isAddressValid(request.addresses()),
                UserErrorCode.USER_ADDRESS_EMPTY.getMessage()
        );
        // Add validation phones for phones
        validation.addValidationRule(
                req -> isPhonesValid(request.phones()),
                UserErrorCode.USER_PHONE_EMPTY.name()
        );
        // Validate the UserRequest and return the result
        return validation.validate(request);
    }

    // Method to check if the length of a string is valid
    private static boolean isLengthValid(String value, int min, int max) {
        return value.length() >= min && value.length() <= max; // Check if length is within bounds
    }

    // Overloaded method to check if the length of a string is valid (only minimum)
    private static boolean isLengthValid(String value, int min) {
        return value.length() <= min; // Check if length is within the maximum
    }

    // Method to validate the date birth
    private static boolean validateAge(LocalDate dateBirth) {
        LocalDate currentDat = LocalDate.now();
        Period period = Period.between(dateBirth ,currentDat);
        return period.getYears() >= 18;
    }

    // Method to validate the email format
    private static boolean validateEmail(String mail) {
        return mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"); // Check if email matches the regex
    }

    // Method to validate the password format
    private static boolean isPasswordValid(String password, int min) {
        Predicate<String> isPasswordValid = p -> p.length() >= min &&
                p.matches(".*[a-z].*") &&
                p.matches(".*[A-Z].*") &&
                p.matches(".*[0-9].*") &&
                p.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        return isPasswordValid.test(password);
    }

    // Method to validate that the gender is not null
    private static boolean validateContentGender(Gender gender) {
        return gender != null; // Ensure gender is provided
    }

    // Method to validate that the address is not empty
    private static boolean isAddressValid(Set<AddressRequest> address) {
        return address != null && !address.isEmpty(); // Ensure address is provided
    }

    // Method to validate the size of the phone set
    private static boolean isPhonesValid(Set<PhoneRequest> phones) {
        return phones != null && !phones.isEmpty();
    }
}
