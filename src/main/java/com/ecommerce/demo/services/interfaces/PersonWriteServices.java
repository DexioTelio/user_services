package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.RegistrationRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.PersonResponse;
import com.ecommerce.demo.util.Result;

public interface PersonWriteServices {
    Result<Void> registerPerson(RegistrationRequest request);
    Result<PersonResponse> update(UserRequest request);
    Result<PersonResponse>  delete(UserRequest request);
}
