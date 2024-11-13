package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface PersonQueryRepository {
    User findById(Long id);
    Try<Result<Boolean>> exists(String email);
}
