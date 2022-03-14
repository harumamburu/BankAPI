package org.kowalsky.bankingapi.service;

import org.kowalsky.bankingapi.client.AlphaClient;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.repository.MongoRepository;

import javax.inject.Inject;

public class CurrenciesService {

    private final AlphaClient alphaClient;
    private final MongoRepository repository;

    @Inject
    public CurrenciesService(AlphaClient alphaClient, MongoRepository repository) {
        this.alphaClient = alphaClient;
        this.repository = repository;
    }

    public CurrencyRates getCurrencies() {
        alphaClient.setBankingApi(repository.getApiMetaInfo("ALPHA"));
        return alphaClient.getCurrencies();
    }
}
