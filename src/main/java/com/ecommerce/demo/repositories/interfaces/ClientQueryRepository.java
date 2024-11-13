package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface ClientQueryRepository {
  Person findById(Long id);
  Try<Result<Boolean>> exists(Long id);
}

