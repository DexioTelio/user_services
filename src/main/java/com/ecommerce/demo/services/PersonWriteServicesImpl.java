package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.*;
import com.ecommerce.demo.dto.response.PersonResponse;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.model.Person;
import com.ecommerce.demo.enums.PersonErrorCode;
import com.ecommerce.demo.repositories.PersonQueryRepositoryImpl;
import com.ecommerce.demo.repositories.PersonWriteRepositoryImpl;
import com.ecommerce.demo.services.interfaces.PersonWriteServices;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Either;
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
import java.util.function.BiFunction;

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
      Try<Boolean> personExists = personQueryRepository.exists(request.email());
      if (personExists.isFailure()) {
        logger.error("Error al verificar la existencia de la persona: {}", request.email());
        return Result.failure(DatabaseError.QUERY_EXECUTION_ERROR.getMessage() + ": " + personExists.getCause().getMessage()); // Handle failure in querying
      }

      Boolean personExistsResult = personExists.get();
      if (Boolean.TRUE.equals(personExistsResult)) {
        logger.warn("El usuario ya existe: {}", request.email());
        return Result.failure(PersonErrorCode.PERSON_ALREADY_EXISTS.getMessage()); // Return error if person exists
      }
      logger.info("el usuario no está en la base de datos");

      // Create a new person from the request
      Either<String, Long> creationPersonResult = createPerson(request, status);
      if (creationPersonResult.isLeft()) {
        return Result.failure(creationPersonResult.getLeft());
      }

      logger.info("creacion de person exitosa");
      Long personId = creationPersonResult.get();

//      CompletableFuture<Result<Void>> clientFuture = createClientAsync(personId, request.client());
//      CompletableFuture<Result<Void>> addressFuture = createAsync(personId, request.addresses(), addressWriteServices::create, status);
//      CompletableFuture<Result<Void>> phonesFuture = createAsync(personId, request.phones(), phonesWriteServices::create, status);
      // create a new client, address and phone from the request
//      CompletableFuture<Void> resultOperation = CompletableFuture.allOf(clientFuture, addressFuture, phonesFuture)
//                      .thenRun(() -> {
//                        logger.info("Operaciones completadas");
//                      });

      logger.info("Proceso de creación del cliente finalizado con éxito: {}");
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

  public Either<String, Long> createPerson(RegistrationRequest request, TransactionStatus status) {
    Person person = new Person.Builder()
            .setFirstName(request.firstName())
            .setLastName(request.lastName())
            .setDateBirth(request.dateBirth())
            .setEmail(request.email())
            .setPassword(passwordEncoder.encode(request.password()))
            .setGender(request.gender())
            .setProfileImageUrl(request.profileImageUrl())
            .setTermsAccepted(request.termsAccepted())
            .build();

    Try<Long> personCreationResult = personWriteRepository.create(person);
    if (personCreationResult.isFailure()) {
      logger.error("Error al crear el usuario: {}, {}", person.getFirstName() + " " + person.getLastName(), personCreationResult.getCause().getMessage());
      status.setRollbackOnly();
      return Either.left(PersonErrorCode.PERSON_CREATION_FAILURE.getMessage() + ": " + personCreationResult.getCause().getMessage()); // Return error if person creation fails
    }

    logger.info("Person creado con exito");
    return Either.right(personCreationResult.get());
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

  private <T> CompletableFuture<Result<Void>> createAsync(Long personId, Set<T> requests, BiFunction<Long, T, Result<Void>> servicesMethod, TransactionStatus status) {
    List<CompletableFuture<Result<Void>>> futures = requests.stream()
            .map(request -> CompletableFuture.supplyAsync(() -> servicesMethod.apply(personId, request)))
            .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApplyAsync(v -> {
              for (CompletableFuture<Result<Void>> future : futures) {
                Result<Void> result = future.join();
                if (result.isFailure()) {
                  logger.error("Error en la operación");
                  status.setRollbackOnly();
                  return Result.failure("Error en la operación");
                }
              }
              return Result.success();
            });
  }
}
