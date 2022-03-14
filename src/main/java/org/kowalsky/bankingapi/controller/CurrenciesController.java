package org.kowalsky.bankingapi.controller;

import org.kowalsky.bankingapi.client.AlphaClient;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.service.CurrenciesService;

import javax.inject.Inject;

public class CurrenciesController {

    private final CurrenciesService currenciesService;

    @Inject
    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    public CurrencyRates getCurrencies() {
        return currenciesService.getCurrencies();
    }
}
