package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.LoginRequest;
import com.ecommerce.demo.dto.request.RegistrationRequest;
import com.ecommerce.demo.dto.response.PersonResponse;
import io.vavr.control.Either;

public interface AuthServices {
    Either<String, String> authenticate(LoginRequest request);
    //Either<String, String> refreshToken(TokenRefreshRequest request);
    void logout(String token);
    Either<String, PersonResponse> register(RegistrationRequest request);
    boolean validateToken(String token);
    //UserDetails getUserDetails(String token);
}
