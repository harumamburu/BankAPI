package org.kowalsky.bankingapi.service;

public class NoBankClientFoundException extends RuntimeException {

    private final String bankCode;

    public NoBankClientFoundException(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }
}
