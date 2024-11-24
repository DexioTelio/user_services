package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.util.Result;

public interface AddressWriteServices {
    Result<Void> create(Long userId, AddressRequest request);
    Result<AddressResponse> update(AddressRequest request);
    Result<AddressResponse>  delete(AddressRequest request);
}