package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface PersonQueryRepository {
  Try<Person> findById(Long id);
  Try<Person> findByUsername(String username);
  Try<Boolean> exists(String email);
}
