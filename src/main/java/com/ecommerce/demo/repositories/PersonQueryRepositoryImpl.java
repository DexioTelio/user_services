package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.PersonQueryRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonQueryRepositoryImpl implements PersonQueryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonQueryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person findById(Long id) {
        return null;
    }

    @Override
    public Try<Result<Boolean>> exists(String email) {
        String sql = "SELECT EXISTS (SELECT 1 FROM \"Users\" WHERE email = ?)";

        return Try.of(() ->
                        Optional.ofNullable(
                                jdbcTemplate.queryForObject(sql, new Object[]{email}, Boolean.class)
                        )
                        .orElse(false)
                )
                .map(Result::success)
                .recover(e -> Result.failure(
                        DatabaseError.QUERY_EXECUTION_ERROR.getMessage() + ": " + e.getMessage()));
    }
}
