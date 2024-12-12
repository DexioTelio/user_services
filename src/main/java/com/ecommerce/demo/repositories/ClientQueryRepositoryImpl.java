package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.repositories.interfaces.ClientQueryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClientQueryRepositoryImpl implements ClientQueryRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ClientQueryRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Client findById(Long id) {
    return null;
  }

  @Override
  public Try<Boolean> exists(Long personId) {
    String sql = " SELECT EXISTS (SELECT 1 FROM clients WHERE personId = ?";

    return Try.of(() -> jdbcTemplate.queryForObject(sql, new Object[]{personId}, Boolean.class));
  }
}
