package org.kowalsky.bankingapi.controller;

import javax.inject.Inject;

public class CurrenciesController {

    @Inject
    public CurrenciesController() {

    }

    public String getCurrencies() {
        return "Hello World!";
    }
}
