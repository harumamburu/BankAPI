package org.kowalsky.bankingapi.service;

import org.kowalsky.bankingapi.client.AlphaClient;
import org.kowalsky.bankingapi.client.CurrenciesClient;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.repository.MongoRepository;

import javax.inject.Inject;
import java.util.Set;

public class CurrenciesService {

    private final Set<CurrenciesClient> currenciesClients;
    private final MongoRepository repository;

    @Inject
    public CurrenciesService(Set<CurrenciesClient> currenciesClients, MongoRepository repository) {
        this.currenciesClients = currenciesClients;
        this.repository = repository;
    }

    public CurrencyRates getCurrencies(String bankCode) {
        CurrenciesClient client = currenciesClients.stream()
                .filter(currenciesClient -> currenciesClient.getBankCode().equalsIgnoreCase(bankCode))
                .findFirst()
                .orElseThrow(() -> new NoBankClientFoundException(bankCode));
        client.setBankingApi(repository.getApiMetaInfo("ALPHA"));
        return client.getCurrencies();
    }
}
