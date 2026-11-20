package org.ml.frauddetection.record;

public record FraudResponse(String txId, boolean fraud) {
}
