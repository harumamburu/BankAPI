package org.kowalsky.bankingapi.client;

import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.eclipse.jetty.http.HttpStatus;
import org.kowalsky.bankingapi.client.exception.OpenAPIRequestException;
import org.kowalsky.bankingapi.model.BankingApiMeta;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.model.alpha.AlphaCurrencyRates;
import org.kowalsky.bankingapi.model.alpha.AlphaShortError;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static java.lang.String.format;

public class AlphaClient implements CurrenciesClient {

    private static final String ALPHA_CODE = "ALPHA";

    private final HttpClient httpClient;
    private final Mapper mapper;
    private BankingApiMeta bankingApi;

    @Inject
    public AlphaClient(@Named("alphaHttpClient") HttpClient httpClient,
                       Mapper mapper) {
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    @Override
    public String getBankCode() {
        return ALPHA_CODE;
    }

    @Override
    public CurrencyRates getCurrencies() {
        try {
            HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                            .uri(URI.create(
                                    bankingApi.getApiBase() + bankingApi.getVersion() + bankingApi.getCurrencyEndpoint()))
                            .GET()
                            .build(), BodyHandlers.ofString());

            if (HttpStatus.isSuccess(response.statusCode())) {
                return mapper.map(new Gson().fromJson(response.body(), AlphaCurrencyRates.class),
                        CurrencyRates.class);
            } else {
                try {
                    AlphaShortError error = new Gson().fromJson(response.body(), AlphaShortError.class);
                    throw new OpenAPIRequestException(format("Request to AlphaBank API failed: %s. Code %d",
                            error.getMessage(), error.getCode()), response.statusCode());
                } catch (JsonSyntaxException e) {
                    throw new OpenAPIRequestException("Request to AlphaBank API failed", response.statusCode());
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new OpenAPIRequestException("Request to AlphaBank API failed: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR_500);
        }
    }

    public void setBankingApi(BankingApiMeta bankingApi) {
        this.bankingApi = bankingApi;
    }
}
