package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.util.Result;

public interface PersonWriteRepository {
    Result<Long> create(Person person);
    void update(Person person);
    void delete(Long id);
}
