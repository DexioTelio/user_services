package com.ecommerce.demo.dto.error;

import java.time.LocalDateTime;
import java.util.Set;

public class ErrorResponse {
    private final String errorCode;
    private final Set<String> messages;
    private final String message;
    private final ErrorDetails details;
    private final LocalDateTime timestamp;

    public ErrorResponse(String errorCode, Set<String> messages, String message, ErrorDetails details) {
        this.errorCode = errorCode;
        this.messages = messages;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", messages='" + messages + '\'' +
                ", message='" + message + '\'' +
                ", details=" + details +
                ", timestamp=" + timestamp +
                '}';
    }

    public static class ErrorDetails {
        private final String providedValue;
        private final Set<String> acceptedValues;

        public ErrorDetails(String providedValue, Set<String> acceptedValues) {
            this.providedValue = providedValue;
            this.acceptedValues = acceptedValues;
        }

        public String getProvidedValue() {
            return providedValue;
        }

        public Set<String> getAcceptedValues() {
            return acceptedValues;
        }

        @Override
        public String toString() {
            return "ErrorDetails{" +
                    "providedValue='" + providedValue + '\'' +
                    ", acceptedValues=" + acceptedValues +
                    '}';
        }
    }
}
