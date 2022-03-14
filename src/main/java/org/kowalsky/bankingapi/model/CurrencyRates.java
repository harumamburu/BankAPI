package org.kowalsky.bankingapi.model;

import java.util.List;

public class CurrencyRates {

    private List<CurrencyRate> rates;

    public List<CurrencyRate> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRate> rates) {
        this.rates = rates;
    }
}
