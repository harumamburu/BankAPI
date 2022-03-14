package org.kowalsky.bankingapi.client;

import org.kowalsky.bankingapi.model.BankingApiMeta;
import org.kowalsky.bankingapi.model.CurrencyRates;

public interface CurrenciesClient {

    CurrencyRates getCurrencies();
    void setBankingApi(BankingApiMeta alpha);
    String getBankCode();
}
