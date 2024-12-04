package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.error.ErrorResponse;
import com.ecommerce.demo.dto.request.LoginRequest;
import com.ecommerce.demo.dto.request.RegistrationRequest;
import com.ecommerce.demo.services.PersonWriteServicesImpl;
import com.ecommerce.demo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final PersonWriteServicesImpl personWriteServices;

    @Autowired
    public AuthController(PersonWriteServicesImpl personWriteServices) {
        this.personWriteServices = personWriteServices;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        logger.info("Attempting to register user: {}", request.firstName() + " " + request.lastName());

        // Call the service to create the user and store the response
        Result<Void> response = personWriteServices.registerPerson(request);

        // Check if user creation was successful
        if (response.isFailure()) {
            logger.error("User creation failed: {}", response.getErrors());
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            "User Creation Failed", response.getErrors(), response.getError(), null)); // Return a 400 with errors
        }

        // Return a 201 Created response if the user was successfully created
        logger.info("User successfully created: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Result.success("User successfully created: " + response)); // Return the success
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok("bien");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }
}
