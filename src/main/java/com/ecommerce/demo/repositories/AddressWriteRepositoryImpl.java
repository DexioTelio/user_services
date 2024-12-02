package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.model.Address;
import com.ecommerce.demo.enums.AddressErrorCode;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.AddressWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressWriteRepositoryImpl implements AddressWriteRepository {
    private static final Logger logger = LoggerFactory.getLogger(AddressWriteRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result<Void> create(Address address) {
        String sql = "INSERT INTO address (user_id, street, street_number, apartment_number, " +
                "neighborhood, city, state, postal_code, country, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        return Try.run(() -> {
                    jdbcTemplate.update(sql, address.getPersonId(),
                            address.getStreet(),
                            address.getStreetNumber(),
                            address.getApartmentNumber(),
                            address.getNeighborhood(),
                            address.getCity(),
                            address.getState(),
                            address.getPostalCode(),
                            address.getCountry());
                    logger.info("Dirección insertada exitosamente");
                })
                .map(Result::success)
                .getOrElseGet(e -> {
                    //esto hay que personalizarlo
                    logger.error("Error al insertar dirección: {}", e.getMessage());
                    return Result.failure(
                            DatabaseError.INSERTION_ERROR.getMessage() + ": " +
                                    AddressErrorCode.ADDRESS_ALREADY_EXISTS.getMessage());
                });
    }

    @Override
    public Result<Void> delete(Long id) {
        return null;
    }
}
