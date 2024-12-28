package com.ecommerce.demo.services;


import com.ecommerce.demo.dto.request.LoginRequest;
import com.ecommerce.demo.dto.request.RegistrationRequest;
import com.ecommerce.demo.dto.response.PersonResponse;
import com.ecommerce.demo.jwt.JwtTokenServices;
import com.ecommerce.demo.repositories.PersonWriteRepositoryImpl;
import com.ecommerce.demo.repositories.interfaces.PersonQueryRepository;
import com.ecommerce.demo.services.interfaces.AuthServices;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServicesImpl implements AuthServices  {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private final PersonWriteRepositoryImpl personWriteRepository;
    private final PersonQueryRepository personQueryRepository;

    @Autowired
    public AuthServicesImpl(PersonWriteRepositoryImpl personWriteRepository,
                            PersonQueryRepository personQueryRepository,
                            AuthenticationManager authenticationManager,
                            JwtTokenServices jwtTokenServices) {
        this.personWriteRepository = personWriteRepository;
        this.personQueryRepository = personQueryRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public Either<String, String> authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            return Either.right(jwtTokenServices.generateToken(authentication));
        }
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public Either<String, PersonResponse> register(RegistrationRequest request) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
