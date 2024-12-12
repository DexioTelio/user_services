package com.ecommerce.demo.repositories;

import com.ecommerce.demo.enums.ClientErrorCode;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.repositories.interfaces.ClientWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientWriteRepositoryImpl implements ClientWriteRepository {
  private final Logger logger = LoggerFactory.getLogger(ClientWriteRepositoryImpl.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ClientWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Result<Void> create(Client client) {
    String sql = "INSERT INTO clients (person_Id, loyalty_points, preferred_payment_method, " +
            "customer_segment, last_purchase_date, created_at, update_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";


    return Try.run(() -> {
      jdbcTemplate.update(sql, client.getPersonId(),
              client.getLoyaltyPoints(),
              client.getPreferredPaymentMethod(),
              client.getCustomerSegment(),
              client.getLastPurchaseDate());
      logger.info("client creado correctamente");
    })
            .map(Result::success)
            .getOrElseGet(e -> {
              logger.error("Error al insertar client: {}", e.getMessage());
              return Result.failure(
                      DatabaseError.INSERTION_ERROR.getMessage() + ": " + e.getMessage()
              );
            });
  }
}
