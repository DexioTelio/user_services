package com.ecommerce.demo.repositories;

import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class PhonesQueryRepositoryImpl {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PhonesQueryRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

    public Try<Result<Boolean>> exists(String phoneNumber) {
      String sql = "SELECT EXISTS (SELECT 1 FROM phones WHERE phone_number = ?";

      return Try.of(() ->
              Optional.ofNullable(
                      jdbcTemplate.queryForObject(sql, new Object[]{phoneNumber}, Boolean.class)
              )
              .orElse(false)
      )
              .map(Result::success)
              .recover(e -> Result.failure(
                      DatabaseError.QUERY_EXECUTION_ERROR.getMessage() + ": " +  e.getMessage()
              ));
  }
}
