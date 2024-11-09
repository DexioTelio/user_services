package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.PhoneRequest;
import com.ecommerce.demo.repositories.interfaces.PhonesWriteRepository;
import com.ecommerce.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PhonesWriteRepositoryImpl implements PhonesWriteRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhonesWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result<Void> create(Long id, PhoneRequest request) {
        return null;
    }

    @Override
    public Result<String> findById(Long id) {
        return null;
    }
}
