package org.kowalsky.bankingapi.model.mapper;

import org.kowalsky.bankingapi.model.CurrencyRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyRatesMap {

    private Map<String, List<CurrencyRate>> rates;

    public CurrencyRatesMap() {
        this.rates = new HashMap<>();
    }

    public Map<String, List<CurrencyRate>> getRates() {
        return rates;
    }

    public void setRates(Map<String, List<CurrencyRate>> rates) {
        this.rates = rates;
    }
}
