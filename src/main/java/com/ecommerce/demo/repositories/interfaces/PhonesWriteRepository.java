package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.dto.request.PhoneRequest;
import com.ecommerce.demo.model.Phone;
import com.ecommerce.demo.util.Result;

public interface PhonesWriteRepository {
    Result<Void> create(Phone phone);
    Result<String> findById(Long id);
}
