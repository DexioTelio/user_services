package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.PersonWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.postgresql.util.PGobject;
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
    public  PersonWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result<Long> create(Person person) {
        String sql = "INSERT INTO person (firstname, lastname, date_birth, email, password, " +
                "gender, profile_image_url, terms_accepted,) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        // Attempt to insert a new person into the database
        return Try.of(() -> {
                    // Execute the query and retrieve the generated user ID
                    Long personId = jdbcTemplate.queryForObject(sql, new Object[]{
                            person.getFirstName(), person.getLastName(), person.getDateOfBirth(),
                            person.getEmail(), person.getPassword(), person.getGender().getValue().toLowerCase(),
                            person.getProfileImageUrl(), person.isTermsAccepted()
                    }, Long.class);
                    return Result.success(personId); // Log user creation
                })
        .getOrElseGet(e -> Result.failure(DatabaseError.INSERTION_ERROR.getMessage() + ": " + e.getMessage()));
    }

    @Override
    public void update(Person person) {}

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"Users\" WHERE id = ?";

        // Attempt to delete the user with the specified ID
        Try.of(() -> {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                logger.warn("No user found with ID: {}", id); // Log if no user was found
                return Result.failure("User not found.");
            }
            logger.info("User with ID: {} deleted successfully", id); // Log successful deletion
            return Result.success("User deleted");
        }).getOrElseGet(e -> {
            logger.error("Error deleting user with ID {}: {}", id, e.getMessage()); // Log the error
            return Result.failure("Error deleting user: " + e.getMessage());
        });
    }

}
