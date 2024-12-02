package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.util.Result;

public interface ClientWriteRepository {
  Result<Void> create(Client client);
}
