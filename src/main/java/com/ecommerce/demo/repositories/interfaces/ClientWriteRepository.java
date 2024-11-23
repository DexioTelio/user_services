package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.util.Result;

public interface ClientWriteRepository {
  Result<Void> create(Long personId, ClientRequest request);
}
