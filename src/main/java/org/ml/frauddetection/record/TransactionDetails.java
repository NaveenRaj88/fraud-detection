package org.ml.frauddetection.record;

public record TransactionDetails(String txId,
                                 float distanceFromLastTransaction,
                                 float ratioToMedianPrice, boolean usedChip,
                                 boolean usedPinNumber, boolean onlineOrder) {
}
