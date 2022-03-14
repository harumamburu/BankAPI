package org.kowalsky.bankingapi.clinet;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class AlphaClient implements CurrenciesClient {

    private final HttpClient httpClient;

    @Inject
    public AlphaClient(@Named("alphaHttpClient") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String getCurrencies() {
        try {
            return httpClient.send(HttpRequest.newBuilder()
                            .uri(URI.create("https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates"))
                            .GET()
                            .build(), BodyHandlers.ofString())
                    .body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
