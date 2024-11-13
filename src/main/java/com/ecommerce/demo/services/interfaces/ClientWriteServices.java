package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.util.Result;

public interface ClientWriteServices {
  Result<String> create(Client client);
}
