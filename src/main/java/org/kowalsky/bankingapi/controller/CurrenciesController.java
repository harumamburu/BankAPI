package org.kowalsky.bankingapi.controller;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.kowalsky.bankingapi.client.exception.OpenAPIRequestException;
import org.kowalsky.bankingapi.model.ErrorModel;
import org.kowalsky.bankingapi.service.CurrenciesService;
import org.kowalsky.bankingapi.service.NoBankClientFoundException;

import javax.inject.Inject;

public class CurrenciesController {

    private final CurrenciesService currenciesService;

    @Inject
    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    public ResponseWrapper getCurrencies(String bankCode) {
        try {
            return new ResponseWrapper(
                    new Gson().toJson(currenciesService.getCurrencies(bankCode)),
                    HttpStatus.OK_200);
        } catch (OpenAPIRequestException e) {
            return new ResponseWrapper(
                    new Gson().toJson(new ErrorModel(e.getMessage(), e.getHttpStatus())),
                    e.getHttpStatus());
        } catch (NoBankClientFoundException e) {
            return new ResponseWrapper(
                    new Gson().toJson(new ErrorModel("Malformed Bank Code: " + e.getBankCode(), HttpStatus.BAD_REQUEST_400)),
                    HttpStatus.BAD_REQUEST_400);
        }
    }

    public ResponseWrapper getCurrencies() {
        try {
            return new ResponseWrapper(
                    new Gson().toJson(currenciesService.getCurrencies()),
                    HttpStatus.OK_200);
        } catch (OpenAPIRequestException e) {
            return new ResponseWrapper(
                    new Gson().toJson(new ErrorModel(e.getMessage(), e.getHttpStatus())),
                    e.getHttpStatus());
        }
    }
}
