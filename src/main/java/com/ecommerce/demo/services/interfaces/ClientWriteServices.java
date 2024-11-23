package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.dto.response.ClientCreatedResponse;
import com.ecommerce.demo.util.Result;

public interface ClientWriteServices {
  Result<ClientCreatedResponse> create(Long person, ClientRequest request);
}
