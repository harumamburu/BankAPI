package org.kowalsky.bankingapi.controller;

import com.google.gson.Gson;
import org.kowalsky.bankingapi.service.CurrenciesService;

import javax.inject.Inject;

public class CurrenciesController {

    private final CurrenciesService currenciesService;

    @Inject
    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    public String getCurrencies() {
        return new Gson().toJson(currenciesService.getCurrencies());
    }
}
