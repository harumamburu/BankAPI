package org.kowalsky.bankingapi;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import dagger.Component;
import org.eclipse.jetty.http.HttpStatus;
import org.kowalsky.bankingapi.client.ClientBindingModule;
import org.kowalsky.bankingapi.client.HttpClientModule;
import org.kowalsky.bankingapi.controller.CurrenciesController;
import org.kowalsky.bankingapi.controller.ResponseWrapper;
import org.kowalsky.bankingapi.model.ErrorModel;
import org.kowalsky.bankingapi.model.mapper.MapperModule;
import org.kowalsky.bankingapi.repository.MongoModule;

import javax.inject.Singleton;

import static spark.Spark.*;

public class Application {

    @Singleton
    @Component(modules = { HttpClientModule.class, MapperModule.class, MongoModule.class, ClientBindingModule.class })
    public interface BankingAPI {
        CurrenciesController currenciesAPI();
        MongoClient exposeMongoClient();
    }

    public static void main(String[] args) {
        CurrenciesController currenciesController = DaggerApplication_BankingAPI.create().currenciesAPI();

        path("/openbanking", () -> {
            path("/v1", () -> {
                path("/currencies", () -> {
                    get("", (request, response) -> {
                        ResponseWrapper resp = currenciesController.getCurrencies(request.queryParams("bankCode"));
                        response.status(resp.statusCode());
                        return resp.body();
                    });
                });
            });

            exception(Exception.class, (exc, request, response) -> {
                response.body(new Gson().toJson(new ErrorModel(exc.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR_500)));
                response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            });
        });
    }
}
