package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface ClientQueryRepository {
  Client findById(Long id);
  Try<Result<Boolean>> exists(Long id);
}

