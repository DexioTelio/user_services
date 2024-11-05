package com.ecommerce.demo.validation;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.RegisterRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.ecommerce.demo.enums.UserErrorCode;
import com.ecommerce.demo.util.Dictionary;
import io.vavr.control.Either;

import java.util.Set;
import java.util.function.Predicate;

public class UserValidation {
    // Private constructor to prevent instantiation
    private UserValidation() {}

    // Method to validate a UserRequest object
    public static Either<Set<String>, RegisterRequest> validateUserRequest(RegisterRequest request) {
        Dictionary<RegisterRequest> validation = new Dictionary<>(); // Create a new Dictionary for validation rules

        // Add validation rules for first name length
        validation.addValidationRule(
                req -> isLengthValid(request.getFirstName(), 3, 50),
                UserErrorCode.USER_FIRSTNAME_LENGTH_FAILURE.getMessage()
        );
        // Add validation rules for last name length
        validation.addValidationRule(
                req -> isLengthValid(request.getLastName(), 3, 50),
                UserErrorCode.USER_LASTNAME_LENGTH_FAILURE.getMessage()
        );
        // Add validation rules for username length
        validation.addValidationRule(
                req -> isLengthValid(request.getUserName(), 3, 50),
                UserErrorCode.USER_USERNAME_LENGTH_FAILURE.getMessage()
        );
        // Add validation rules for phone size
        validation.addValidationRule(
                req -> validatePhonesSize(request.getPhones()),
                UserErrorCode.USER_PHONES_FAILURE.getMessage()
        );
        // Add validation rules for phone format
        validation.addValidationRule(
                req -> validatePhonesMatch(request.getPhones()),
                UserErrorCode.USER_PHONE_FORMAT_FAILURE.getMessage()
        );
        // Add validation rules for gender
        validation.addValidationRule(
                req -> validateContentGender(request.getGender()),
                UserErrorCode.USER_GENDER_FAILURE.getMessage()
        );
        // Add validation rules for role
        validation.addValidationRule(
                req -> validateContentRole(request.getRole()),
                UserErrorCode.USER_ROLE_FAILURE.getMessage()
        );
        // Add validation rules for email format
        validation.addValidationRule(
                req -> validateEmail(request.getEmail()),
                UserErrorCode.USER_EMAIL_FORMAT_FAILURE.getMessage()
        );
        // Add validation rules for age
        validation.addValidationRule(
                req -> validateAge(request.getAge()),
                UserErrorCode.USER_AGE_FAILURE.getMessage()
        );
        // Add validation rules for address
        validation.addValidationRule(
                req -> isAddressValid(request.getAddress()),
                UserErrorCode.USER_ADDRESS_EMPTY.getMessage()
        );
        // Add validation rules for password
        validation.addValidationRule(
                req -> isPasswordValid(request.getPassword(), 14),
                UserErrorCode.USER_PASSWORD_COMPLEXITY_FAILURE.getMessage()
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

    // Method to validate phone format
    private static boolean validatePhonesMatch(Set<String> phones) {
        return phones.stream().allMatch(p -> p.matches("^\\+\\d{1,3}\\d{4,14}$")); // Ensure all phones match the format
    }

    // Method to validate the size of the phone set
    private static boolean validatePhonesSize(Set<String> phones) {
        return !phones.isEmpty() && phones.size() <= 2; // Check that there are 1 to 2 phones
    }

    // Method to validate that the gender is not null
    private static boolean validateContentGender(Gender gender) {
        return gender != null; // Ensure gender is provided
    }

    // Method to validate that the role is not null
    private static boolean validateContentRole(Set<Role> role) {
        return role != null && !role.isEmpty(); // Ensure role is provided
    }

    // Method to validate the email format
    private static boolean validateEmail(String mail) {
        return mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"); // Check if email matches the regex
    }

    // Method to validate that the age is at least 18
    private static boolean validateAge(int age) {
        return age >= 18; // Check if age is valid
    }

    // Method to validate that the address is not empty
    private static boolean isAddressValid(Set<AddressRequest> address) {
        return address != null && !address.isEmpty(); // Ensure address is provided
    }

    private static boolean isPasswordValid(String password, int min) {
        Predicate<String> isPasswordValid = p -> p.length() >= min &&
                p.matches(".*[a-z].*") &&
                p.matches(".*[A-Z].*") &&
                p.matches(".*[0-9].*") &&
                p.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        return isPasswordValid.test(password);
    }
}
