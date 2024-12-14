package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.repositories.interfaces.PersonWriteRepository;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonWriteRepositoryImpl implements PersonWriteRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonWriteRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Try<Long> create(Person person) {
        String sql = "INSERT INTO persons (first_name, last_name, date_birth, email, password, " +
                "gender, profile_image_url, last_login, terms_accepted) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?) RETURNING person_id;";

        // Attempt to insert a new person into the database
        // Execute the query and retrieve the generated user ID
        return Try.of(() -> jdbcTemplate.queryForObject(sql, Long.class,
                person.getFirstName(),
                person.getLastName(),
                person.getDateOfBirth(),
                person.getEmail(),
                person.getGender().getValue().toLowerCase(),
                person.getPassword(),
                person.getProfileImageUrl(),
                person.isTermsAccepted()));
    }

    @Override
    public void update(Person person) {
    }

    @Override
    public void delete(Long id) {
    }
}
