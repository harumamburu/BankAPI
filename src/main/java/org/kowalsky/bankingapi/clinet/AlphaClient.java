package org.kowalsky.bankingapi.clinet;

import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.kowalsky.bankingapi.clinet.exception.OpenAPIRequestException;
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

    private final HttpClient httpClient;
    private final Mapper mapper;

    @Inject
    public AlphaClient(@Named("alphaHttpClient") HttpClient httpClient,
                       Mapper mapper) {
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    @Override
    public CurrencyRates getCurrencies() {
        try {
            HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                            .uri(URI.create("https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates"))
                            .GET()
                            .build(), BodyHandlers.ofString());

            if (HttpStatus.isSuccess(response.statusCode())) {
                return mapper.map(new Gson().fromJson(response.body(), AlphaCurrencyRates.class),
                        CurrencyRates.class);
            } else {
                AlphaShortError error = new Gson().fromJson(response.body(), AlphaShortError.class);
                throw new OpenAPIRequestException(format("Request to AlphaBank API failed: %s. Code %d",
                        error.getMessage(), error.getCode()), response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new OpenAPIRequestException("Request to AlphaBank API failed: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR_500);
        }
    }
}
