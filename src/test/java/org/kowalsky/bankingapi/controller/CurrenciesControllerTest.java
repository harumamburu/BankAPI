package org.kowalsky.bankingapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kowalsky.bankingapi.client.exception.OpenAPIRequestException;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.service.CurrenciesService;
import org.kowalsky.bankingapi.service.NoBankClientFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrenciesControllerTest {

    @InjectMocks
    CurrenciesController controller;

    @Mock
    CurrenciesService currenciesService;

    @Test
    public void test_getCurrencies_withBankCode_success_200WithBodyReturned() {
        CurrencyRates currencyRates = new CurrencyRates();
        when(currenciesService.getCurrencies(anyString())).thenReturn(currencyRates);

        assertThat(controller.getCurrencies(""), is(equalTo(new ResponseWrapper("{}", 200))));
    }

    @Test
    public void test_getCurrencies_withBankCode_throwsOpenAPIRequestException_excCodeWithExcMessageReturned() {
        String errorString = "ERROR!";
        int errorCode = 500;
        when(currenciesService.getCurrencies(anyString())).thenThrow(new OpenAPIRequestException(errorString, errorCode));

        assertThat(controller.getCurrencies(""), is(equalTo(new ResponseWrapper(
                "{\"message\":\"ERROR!\",\"httpStatus\":500}", errorCode))));
    }

    @Test
    public void test_getCurrencies_withBankCode_throwsNoBankClientFoundException_400WithMessageReturned() {
        String bankCode = "ALPA";
        when(currenciesService.getCurrencies(anyString())).thenThrow(new NoBankClientFoundException(bankCode));

        assertThat(controller.getCurrencies(""), is(equalTo(new ResponseWrapper(
                "{\"message\":\"Malformed Bank Code: ALPA\",\"httpStatus\":400}", 400))));
    }
}