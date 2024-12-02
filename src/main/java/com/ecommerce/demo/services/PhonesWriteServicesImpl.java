package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.PhoneRequest;
import com.ecommerce.demo.enums.PhonesErrorCode;
import com.ecommerce.demo.model.Phone;
import com.ecommerce.demo.repositories.PhonesQueryRepositoryImpl;
import com.ecommerce.demo.repositories.PhonesWriteRepositoryImpl;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

public class PhonesWriteServicesImpl {
    private final Logger logger = LoggerFactory.getLogger(PhonesWriteServicesImpl.class);

    private final PhonesWriteRepositoryImpl phonesWriteRepository;
    private final TransactionTemplate transactionTemplate;
    private final PhonesQueryRepositoryImpl phonesQueryRepository;

    @Autowired
    public PhonesWriteServicesImpl(PhonesWriteRepositoryImpl phonesWriteRepository,
                                   TransactionTemplate transactionTemplate,
                                   PhonesQueryRepositoryImpl phonesQueryRepository) {
        this.phonesWriteRepository = phonesWriteRepository;
        this.transactionTemplate = transactionTemplate;
        this.phonesQueryRepository = phonesQueryRepository;
    }

    public Result<Void> create(Long personId, PhoneRequest request) {
        return transactionTemplate.execute(status -> {
            // check is the phones already exists
            Try<Result<Boolean>> phonesExists = phonesQueryRepository.exists(request.phoneNumber());
            if (phonesExists.isFailure()) {
                return HandlePhonesExistsError(phonesExists.getCause());
            }

            Result<Boolean> phonesExistsresult = phonesExists.get();
            if (phonesExistsresult.isFailure() && Boolean.TRUE.equals(phonesExistsresult.getValue())) {
                logger.error("el phone ya exists: {}", request.phoneNumber());
                return Result.failure(PhonesErrorCode.PHONES_CREATION_FAILURE.getMessage());
            }

            return createPhone(personId, request, status);
        });
    }

    private Result<Void> HandlePhonesExistsError(Throwable error) {
        logger.error("Error al verificar la existencia del phone");
        return Result.failure(error.getMessage());
    }

    private Result<Void> createPhone(Long personId, PhoneRequest request, TransactionStatus status) {
        Phone phone = new Phone.Builder()
                .personId(personId)
                .phoneNumber(request.phoneNumber())
                .phoneType(request.phoneType())
                .build();

        Result<Void> phoneCreationResult = phonesWriteRepository.create(phone);
        if (phoneCreationResult.isFailure()) {
            logger.error("Error al crear phone");
            status.setRollbackOnly();
            return Result.failure(PhonesErrorCode.PHONE_ERROR_CREATION.getMessage());
        }

        logger.info("Phone creado con exito");
        return Result.success();
    }
}
