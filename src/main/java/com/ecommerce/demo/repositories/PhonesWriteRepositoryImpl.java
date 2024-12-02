package com.ecommerce.demo.repositories;

import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.model.Phone;
import com.ecommerce.demo.repositories.interfaces.PhonesWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PhonesWriteRepositoryImpl implements PhonesWriteRepository {
    private final Logger logger = LoggerFactory.getLogger(PhonesWriteRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhonesWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result<Void> create(Phone phone) {
        String sql = "INSERT INTO phones (person_id, phone_number, phone_type)";

        return Try.run(() -> {
            jdbcTemplate.update(sql, phone.getPersonId(),
                    phone.getPhoneNumber(), phone.getPhoneType());
            logger.info("Phone insertado correctamente");
        })
                .map(Result::success)
                .getOrElseGet(e -> {
                    logger.error("Erro al crear phones");
                    return Result.failure(
                            DatabaseError.INSERTION_ERROR.getMessage() + ": " + e.getMessage()
                    );
                });
    }

    @Override
    public Result<String> findById(Long id) {
        return null;
    }
}
