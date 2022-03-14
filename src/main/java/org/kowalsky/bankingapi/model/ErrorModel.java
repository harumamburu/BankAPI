package org.kowalsky.bankingapi.model;

public record ErrorModel(String message, Integer httpStatus) {
}
