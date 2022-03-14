package org.kowalsky.bankingapi.client.exception;

public class OpenAPIRequestException extends RuntimeException {

    private final Integer httpStatus;

    public OpenAPIRequestException(String message, Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public OpenAPIRequestException(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}
