package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Client;
import io.vavr.control.Try;

public interface ClientQueryRepository {
  Client findById(Long id);
  Try<Boolean> exists(Long id);
}

