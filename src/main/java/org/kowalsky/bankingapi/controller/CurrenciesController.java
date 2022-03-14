package org.kowalsky.bankingapi.controller;

import org.kowalsky.bankingapi.clinet.AlphaClient;

import javax.inject.Inject;

public class CurrenciesController {

    private final AlphaClient alphaClient;

    @Inject
    public CurrenciesController(AlphaClient alphaClient) {
        this.alphaClient = alphaClient;
    }

    public String getCurrencies() {
        return alphaClient.getCurrencies();
    }
}
