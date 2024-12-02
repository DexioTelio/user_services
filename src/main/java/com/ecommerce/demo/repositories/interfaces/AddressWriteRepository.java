package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Address;
import com.ecommerce.demo.util.Result;

public interface AddressWriteRepository {
    Result<Void> create(Address address);
    Result<Void> delete(Long id);
}
