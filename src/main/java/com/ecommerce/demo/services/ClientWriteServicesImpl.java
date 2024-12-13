package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.enums.ClientErrorCode;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.model.Client;
import com.ecommerce.demo.repositories.ClientQueryRepositoryImpl;
import com.ecommerce.demo.repositories.ClientWriteRepositoryImpl;
import com.ecommerce.demo.services.interfaces.ClientWriteServices;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ClientWriteServicesImpl implements ClientWriteServices {
  private static final Logger logger = LoggerFactory.getLogger(ClientWriteServicesImpl.class);

  private final TransactionTemplate transactionTemplate;
  private final ClientQueryRepositoryImpl clientQueryRepository;
  private final ClientWriteRepositoryImpl clientWriteRepository;

  @Autowired
  public ClientWriteServicesImpl(TransactionTemplate transactionTemplate,
                                 ClientQueryRepositoryImpl clientQueryRepository,
                                 ClientWriteRepositoryImpl clientWriteRepository) {
    this.transactionTemplate = transactionTemplate;
    this.clientQueryRepository = clientQueryRepository;
    this.clientWriteRepository = clientWriteRepository;
  }

  @Override
  public Result<Void> create(Long personId, ClientRequest request) {
    return transactionTemplate.execute(status -> {
      // Check if the client already exists based on the id
      Try<Boolean> clientExists = clientQueryRepository.exists(personId);
      if (clientExists.isFailure()) {
        return handleClientExistenceError(clientExists.getCause());
      }

      Boolean clientExistsResult = clientExists.get();
      if (Boolean.TRUE.equals(clientExistsResult)) {
        logger.error("El cliente ya existe: {}", personId);
        return Result.failure(ClientErrorCode.CLIENT_ALREADY_EXISTS.getMessage());
      }

      return createClient(personId, request, status);
    });
  }

  @Secured({"USER", "ADMIN"})
  public void update(Client client) {
  }

  private Result<Void> handleClientExistenceError(Throwable error) {
    logger.error("Error al verificar la existencia del cliente: {}", error.getMessage());
    return Result.failure(DatabaseError.QUERY_EXECUTION_ERROR.getMessage() + error.getMessage());
  }

  private Result<Void> createClient(Long personId, ClientRequest request, TransactionStatus status) {
    Client client = new Client.Builder()
            .personId(personId)
            .loyaltyPoints(request.loyaltyPoints())
            .preferredPaymentMethod(request.preferredPaymentMethod())
            .customerSegment(request.customerSegment())
            .lastPurchaseDate(request.lastPurchaseDate())
            .build();

    Result<Void> clientCreationResult = clientWriteRepository.create(client);
    if (clientCreationResult.isFailure()) {
      logger.error("Error al crear al cliente para person: {}", personId);
      status.setRollbackOnly();
      return Result.failure(ClientErrorCode.CLIENT_CREATION_FAILURE.getMessage() + ": " + String.join(clientCreationResult.getError()));
    }

    logger.info("Cliente creado con exito para el person: {}", personId);
    return Result.success();
  }
}
