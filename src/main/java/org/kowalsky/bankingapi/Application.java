package org.kowalsky.bankingapi;

import com.google.gson.Gson;
import dagger.Component;
import org.kowalsky.bankingapi.client.HttpClientModule;
import org.kowalsky.bankingapi.client.exception.OpenAPIRequestException;
import org.kowalsky.bankingapi.controller.CurrenciesController;
import org.kowalsky.bankingapi.model.ErrorModel;
import org.kowalsky.bankingapi.model.mapper.MapperModule;

import javax.inject.Singleton;

import static spark.Spark.*;

public class Application {

    @Singleton
    @Component(modules = { HttpClientModule.class, MapperModule.class })
    public interface BankingAPI {
        CurrenciesController currenciesAPI();
    }

    public static void main(String[] args) {
        CurrenciesController currenciesController = DaggerApplication_BankingAPI.create().currenciesAPI();

        path("/openbanking", () -> {
            path("/v1", () -> {
                path("/currencies", () -> {
                    get("", (request, response) -> new Gson().toJson(currenciesController.getCurrencies()));
                });

                exception(OpenAPIRequestException.class, (exc, request, response) -> {
                        response.body(new Gson().toJson(new ErrorModel(exc.getMessage(), exc.getHttpStatus())));
                        response.status(exc.getHttpStatus());
                });
            });
        });
    }
}
