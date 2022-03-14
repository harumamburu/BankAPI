package org.kowalsky.bankingapi.controller;

import org.kowalsky.bankingapi.client.AlphaClient;
import org.kowalsky.bankingapi.model.CurrencyRates;

import javax.inject.Inject;

public class CurrenciesController {

    private final AlphaClient alphaClient;

    @Inject
    public CurrenciesController(AlphaClient alphaClient) {
        this.alphaClient = alphaClient;
    }

    public CurrencyRates getCurrencies() {
        return alphaClient.getCurrencies();
    }
}
