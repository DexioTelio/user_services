package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.repositories.interfaces.PersonQueryRepository;
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
    public Try<Person> findById(Long id) {
        return null;
    }

    @Override
    public Try<Person> findByUsername(String username) {
        String sql = "SELECT EXISTS (SELECT 1 FROM persons WHERE first_name || ' ' || last_name = ?)";
        return Try.of(() ->
                jdbcTemplate.queryForObject(sql, Person.class, username));
    }

    @Override
    public Try<Boolean> exists(String email) {
        String sql = "SELECT EXISTS (SELECT 1 FROM persons WHERE email = ?)";
        return Try.of(() ->
                jdbcTemplate.queryForObject(sql, Boolean.class, email));
    }
}
