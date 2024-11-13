package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.util.Result;

public interface ClientWriteServices {
  Result<String> create(ClientRequest request);
}
