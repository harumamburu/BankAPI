package org.kowalsky.bankingapi.service;

import org.kowalsky.bankingapi.client.CurrenciesClient;
import org.kowalsky.bankingapi.model.BankingApiMeta;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.model.mapper.CurrencyRatesMap;
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
        BankingApiMeta apiMetaInfo = repository.getApiMetaInfo(bankCode.toUpperCase());
        CurrenciesClient client = currenciesClients.stream()
                .filter(currenciesClient -> currenciesClient.getBankCode().equalsIgnoreCase(bankCode))
                .findFirst()
                .orElseThrow(() -> new NoBankClientFoundException(bankCode));
        client.setBankingApi(apiMetaInfo);
        return client.getCurrencies();
    }

    public CurrencyRatesMap getCurrencies() {
        return repository.getAllApiMetaInfo().stream()
                .flatMap(apiMetaInfo -> currenciesClients.stream()
                        .filter(currenciesClient -> currenciesClient.getBankCode().equalsIgnoreCase(apiMetaInfo.getBankCode()))
                        .peek(currenciesClient -> currenciesClient.setBankingApi(apiMetaInfo)))
                .collect(CurrencyRatesMap::new,
                        (currencyRatesMap, currenciesClient) -> currencyRatesMap.getRates().put(
                                currenciesClient.getBankCode(), currenciesClient.getCurrencies().getRates()),
                        (currencyRatesMap, currencyRatesMap2) -> currencyRatesMap.getRates().putAll(currencyRatesMap2.getRates()));
    }
}
