package org.ml.frauddetection.controller;

import ai.djl.inference.Predictor;
import ai.djl.translate.TranslateException;
import jakarta.annotation.Resource;
import org.ml.frauddetection.record.FraudResponse;
import org.ml.frauddetection.record.TransactionDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class FraudDetectionInferenceController {

    @Resource
    private Supplier<Predictor<TransactionDetails, Boolean>> predictorSupplier;

    @PostMapping("/inference")
    FraudResponse detectFraud(@RequestBody TransactionDetails transactionDetails) throws TranslateException {
        try (var predictor = predictorSupplier.get()) {
            boolean isFraud = predictor.predict(transactionDetails);
            return new FraudResponse(transactionDetails.txId(), isFraud);
        }
    }

}
