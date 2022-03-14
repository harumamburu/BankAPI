package org.kowalsky.bankingapi;

import org.kowalsky.bankingapi.controller.CurrenciesController;

import static spark.Spark.get;
import static spark.Spark.path;

public class Application {

    public static void main(String[] args) {
        CurrenciesController currenciesController = new CurrenciesController();

        path("/openbanking", () -> {
            path("/v1", () -> {
                path("/currencies", () -> {
                    get("", (request, response) -> currenciesController.getCurrencies());
                });
            });
        });
    }
}
