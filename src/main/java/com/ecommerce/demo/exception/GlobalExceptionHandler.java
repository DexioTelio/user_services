package com.ecommerce.demo.exception;

import com.ecommerce.demo.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_SERVER_ERROR", null,
                        "An unexpected error occurred.", null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();
        String providedValue = extractProvidedValue(errorMessage);
        Set<String> acceptedRoles = Set.of("CLIENT", "ADMIN", "VISITOR");
        Set<String> acceptedGenders = Set.of("MALE", "FEMALE", "OTHER");
        Set<String> acceptedPhoneTypes = Set.of("MOBILE", "FIXED", "WORK", "EMERGENCY");

        if (errorMessage.contains("Role")) {
            return createErrorResponse("INVALID_ROLE", providedValue, acceptedRoles);
        } else if (errorMessage.contains("Gender")) {
            return createErrorResponse("INVALID_GENDER", providedValue, acceptedGenders);
        } else if (errorMessage.contains("phone_type")) {
            return createErrorResponse("INVALID_PHONE_TYPE", providedValue, acceptedPhoneTypes);
        }


        // respuesta genérica
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse("INVALID_INPUT", null, "Entrant invalid.",
                        new ErrorResponse.ErrorDetails(providedValue, null))
        );
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(
            String errorType, String providedValue, Set<String> acceptedValues) {
        ErrorResponse errorResponse = new ErrorResponse(
                errorType,
                null,
                "The value provided is invalid.",
                new ErrorResponse.ErrorDetails(providedValue, acceptedValues)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private String extractProvidedValue(String errorMessage) {
        if (errorMessage.contains("Cannot deserialize value of type")) {
            String[] parts = errorMessage.split("\"");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return "unknown";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Set<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.toSet());

        return ResponseEntity.badRequest().body(
                new ErrorResponse("VALIDATION_ERROR", errorMessages, null, null));
    }
}
