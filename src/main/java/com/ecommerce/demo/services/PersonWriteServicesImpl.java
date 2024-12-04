package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.*;
import com.ecommerce.demo.dto.response.PersonResponse;
import com.ecommerce.demo.enums.PhonesErrorCode;
import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.enums.PersonErrorCode;
import com.ecommerce.demo.repositories.PersonQueryRepositoryImpl;
import com.ecommerce.demo.repositories.PersonWriteRepositoryImpl;
import com.ecommerce.demo.services.interfaces.PersonWriteServices;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

// Service implementation for user writing operations
@Service
public class PersonWriteServicesImpl implements PersonWriteServices {
  private static final Logger logger = LoggerFactory.getLogger(PersonWriteServicesImpl.class);

  private final PersonWriteRepositoryImpl personWriteRepository;
  private final PersonQueryRepositoryImpl personQueryRepository;
  private final ClientWriteServicesImpl clientWriteServices;
  private final AddressWriteServicesImpl addressWriteServices;
  private final BCryptPasswordEncoder passwordEncoder;
  private final TransactionTemplate transactionTemplate;
  private final PhonesWriteServicesImpl phonesWriteServices;

  // Constructor that injects the required repositories and services
  @Autowired
  public PersonWriteServicesImpl(PersonWriteRepositoryImpl personWriteRepository,
                                 PersonQueryRepositoryImpl personQueryRepository,
                                 ClientWriteServicesImpl clientWriteServices,
                                 AddressWriteServicesImpl addressWriteServices,
                                 BCryptPasswordEncoder passwordEncoder,
                                 TransactionTemplate transactionTemplate,
                                 PhonesWriteServicesImpl phonesWriteServices) {
    this.personWriteRepository = personWriteRepository;
    this.personQueryRepository = personQueryRepository;
    this.clientWriteServices = clientWriteServices;
    this.addressWriteServices = addressWriteServices;
    this.passwordEncoder = passwordEncoder;
    this.transactionTemplate = transactionTemplate;
    this.phonesWriteServices = phonesWriteServices;
  }

  @Override
  public Result<Void> registerPerson(RegistrationRequest request) {
    return transactionTemplate.execute(status -> {
      // Check if the person already exists based on the email
      Try<Result<Boolean>> personExists = personQueryRepository.exists(request.email());
      if (personExists.isFailure()) {
        logger.error("Error al verificar la existencia del usuario: {}", personExists.getCause().getMessage());
        return Result.failure(personExists.getCause().getMessage()); // Handle failure in querying
      }

      Result<Boolean> personExistsResult = personExists.get();
      if (personExistsResult.isSuccess() && Boolean.TRUE.equals(personExistsResult.getValue())) {
        logger.warn("El usuario ya existe: {}", request.email());
        return Result.failure(PersonErrorCode.PERSON_ALREADY_EXISTS.getMessage()); // Return error if person exists
      }

      // Create a new person from the request
      Result<Long> creationPersonResult = createPerson(request, status);
      if (creationPersonResult.isFailure()) {
        return Result.failure(creationPersonResult.getError());
      }

      Long personId = creationPersonResult.getValue();

      createClientAsync(personId, request.client());
      createAddressAsync(personId, request.addresses(), status);
      createPhonesAsync(personId, request.phones(), status);


      logger.info("Proceso de creación de usuario finalizado con éxito: {}");
      return Result.success();
    });
  }

  @Override
  public Result<PersonResponse> update(UserRequest request) {
    return null;
  }

  @Override
  public Result<PersonResponse> delete(UserRequest request) {
    return null;
  }

  private Result<Long> createPerson(RegistrationRequest request, TransactionStatus status) {
    Person person = new Person.Builder()
            .setFirstName(request.firstName())
            .setLastName(request.lastName())
            .setDateOfBirth(request.dateBirth())
            .setEmail(request.email())
            .setPassword(passwordEncoder.encode(request.password()))
            .setGender(request.gender())
            .setProfileImageUrl(request.profileImageUrl())
            .setTermsAccepted(request.termsAccepted())
            .build();

    Result<Long> personCreationResult = personWriteRepository.create(person);
    if (personCreationResult.isFailure()) {
      logger.error("Error al crear el usuario: {}", personCreationResult.getErrors());
      status.setRollbackOnly();
      return Result.failure(PersonErrorCode.PERSON_CREATION_FAILURE.getMessage() + ": "
              + String.join(", ", personCreationResult.getErrors())); // Return error if person creation fails
    }

    logger.info("Person creado con exito");
    return Result.success(personCreationResult.getValue());
  }

  private CompletableFuture<Result<Void>> createClientAsync(Long personId, ClientRequest request) {
    return CompletableFuture.supplyAsync(() -> {
      Result<Void> createClientResult = clientWriteServices.create(personId, request);
      if (createClientResult.isFailure()) {
        logger.error("Error al crear al cliente");
        return Result.failure(createClientResult.getError());
      }
      return Result.success();
    });
  }

  private CompletableFuture<Result<Void>> createAddressAsync(Long personId, Set<AddressRequest> request, TransactionStatus status) {
    List<CompletableFuture<Result<Void>>> futures = request.stream()
            .parallel()
            .map(addressRequest -> CompletableFuture.supplyAsync(() ->
                    addressWriteServices.create(personId, addressRequest)))
            .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApplyAsync(v -> {
              for (CompletableFuture<Result<Void>> future: futures) {
                Result<Void> result = future.join();
                if (result.isFailure()) {
                  logger.error("Error al crear address");
                  status.setRollbackOnly();
                  return Result.failure(PersonErrorCode.PERSON_ADDRESS_CREATION_FAILURE.getMessage() + ": "
                          + result.getErrors());
                }
              }
              return Result.success();
            });
  }

  private CompletableFuture<Result<Void>> createPhonesAsync(Long personId, Set<PhoneRequest> request, TransactionStatus status) {
    List<CompletableFuture<Result<Void>>> futures = request.stream()
            .parallel()
            .map(phoneRequest -> CompletableFuture.supplyAsync(() ->
                    phonesWriteServices.create(personId, phoneRequest)))
            .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApplyAsync(v -> {
              for (CompletableFuture<Result<Void>> future: futures) {
                Result<Void> result = future.join();
                if (result.isFailure()) {
                  logger.error("Error al crear phone");
                  status.setRollbackOnly();
                  return Result.failure(PhonesErrorCode.PHONES_CREATION_FAILURE.getMessage() + ": "
                  +result.getErrors());
                }
              }
              return Result.success();
            });
  }
}
