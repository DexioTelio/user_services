package com.ecommerce.demo.repositories;

import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repositories.interfaces.PersonQueryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, first_name || ' ' || last_name AS full_name, date_birth, email, password, " +
                "account_non_expired, credentials_non_expired, account_non_locked, enabled " +
                "FROM persons WHERE first_name || ' ' || last_name = ?";

        return Optional.of(jdbcTemplate.queryForObject(sql, User.class, personRowMapper(email)));
    }

    public List<SimpleGrantedAuthority> getAuthorities(String email) {
        String sql = "SELECT r.role_name FROM roles r " +
                "JOIN persons_role pr ON r.id = pr.role_id " +
                "JOIN persons p ON pr.person_id = p.id " +
                "WHERE email = ?";

        return jdbcTemplate.queryForList(sql, String.class, email)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private RowMapper<User> personRowMapper(String email) {
        return ((rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("full_name"),
                        rs.getDate("date_birth"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("account_non_expired"),
                        rs.getBoolean("credentials_non_expired"),
                        rs.getBoolean("account_non_locked"),
                        rs.getBoolean("enabled"),
                        getAuthorities(email)
                )
        );
    }
}
