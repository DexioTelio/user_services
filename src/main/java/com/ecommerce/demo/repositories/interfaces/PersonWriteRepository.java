package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Person;
import io.vavr.control.Try;

public interface PersonWriteRepository {
    Try<Long> create(Person person);
    void update(Person person);
    void delete(Long id);
}
