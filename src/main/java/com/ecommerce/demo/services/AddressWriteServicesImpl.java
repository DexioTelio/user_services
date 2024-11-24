package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.enums.AddressErrorCode;
import com.ecommerce.demo.repositories.AddressQueryRepositoryImpl;
import com.ecommerce.demo.repositories.AddressWriteRepositoryImpl;
import com.ecommerce.demo.services.interfaces.AddressWriteServices;
import com.ecommerce.demo.util.Result;
import com.ecommerce.demo.validation.AddressValidation;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Set;

// Service class for handling address writing operations
@Service
public class AddressWriteServicesImpl implements AddressWriteServices {
    private static final Logger logger = LoggerFactory.getLogger(AddressWriteServicesImpl.class);
    // Repository for writing addresses
    private final AddressWriteRepositoryImpl addressWriteRepository;
    // Repository for querying addresses
    private final AddressQueryRepositoryImpl addressQueryRepository;
    private final TransactionTemplate transactionTemplate;

    // Constructor that injects the repositories
    @Autowired
    public AddressWriteServicesImpl(AddressWriteRepositoryImpl addressWriteRepository,
                                    AddressQueryRepositoryImpl addressQueryRepository,
                                    TransactionTemplate transactionTemplate) {
        this.addressWriteRepository = addressWriteRepository;
        this.addressQueryRepository = addressQueryRepository;
        this.transactionTemplate = transactionTemplate;
    }

    // Method to create a new address for a user
    @Override
    public Result<Void> create(Long personId, AddressRequest request) {
        return transactionTemplate.execute(status -> {
            // Check if the address already exists
            Either<Set<String>, AddressRequest> addressValidation = AddressValidation.validateAddressRequest(request);
            if (addressValidation.isLeft()) {
                return Result.failure(addressValidation.getLeft());
            }

            Try<Result<Boolean>> addressExists = addressQueryRepository.exists(request);
            if (addressExists.isFailure()) {
                return Result.failure(addressExists.getCause().getMessage()); // Return failure if query fails
            }

            Result<Boolean> addressExistsResult = addressExists.get();
            // If the address already exists, return an error
            if (addressExistsResult.isSuccess() && Boolean.TRUE.equals(addressExistsResult.getValue())) {
                return Result.failure(AddressErrorCode.ADDRESS_ALREADY_EXISTS.getMessage());
            }

            // Attempt to create the address in the repository
            Result<Void> addressCreationResult = addressWriteRepository.create(personId, request);
            if (addressCreationResult.isFailure()) {
                // Return failure if address creation fails, including any errors
                status.setRollbackOnly();
                return Result.failure(AddressErrorCode.ADDRESS_CREATION_FAILURE.getMessage() +
                        ": " + String.join(", ", addressCreationResult.getErrors()));
            }

            logger.info("address creada para el person: {}", personId);
            return Result.success();
        });
    }

    // Method to update an existing address (not implemented)
    @Override
    public Result<AddressResponse> update(AddressRequest request) {
        return null; // Placeholder for update logic
    }

    // Method to delete an existing address (not implemented)
    @Override
    public Result<AddressResponse> delete(AddressRequest request) {
        return null; // Placeholder for delete logic
    }
}
