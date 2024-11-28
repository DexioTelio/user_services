package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.enums.ClientErrorCode;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.ClientWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClientWriteRepositoryImpl implements ClientWriteRepository {
  private final Logger logger = LoggerFactory.getLogger(ClientWriteRepositoryImpl.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ClientWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Result<Void> create(Long personId, ClientRequest request) {
    String sql = "INSERT INTO clients (personId, loyalty_points, preferred_payment_method, " +
            "customer_segment, last_purchase_date, created_at, update_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";


    return Try.run(() -> {
      jdbcTemplate.update(sql, personId,
              request.loyaltyPoints(),
              request.preferredPaymentMethod(),
              request.customerSegment(),
              request.lastPurchaseDate());
      logger.info("client insertado correctamente para el person: {}", personId);
    })
            .map(Result::success)
            .getOrElseGet(e -> {
              logger.error("Error al insertar client: {}", e.getMessage());
              return Result.failure(
                      DatabaseError.INSERTION_ERROR.getMessage() + ": " +
                              ClientErrorCode.CLIENT_ALREADY_EXISTS.getMessage()
              );
            });
  }
}
