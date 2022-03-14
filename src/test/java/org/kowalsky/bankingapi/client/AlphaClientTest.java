package org.kowalsky.bankingapi.client;

import com.github.dozermapper.core.Mapper;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kowalsky.bankingapi.client.exception.OpenAPIRequestException;
import org.kowalsky.bankingapi.model.CurrencyRate;
import org.kowalsky.bankingapi.model.CurrencyRates;
import org.kowalsky.bankingapi.model.alpha.AlphaCurrencyRates;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlphaClientTest {

    @InjectMocks
    AlphaClient client;

    @Mock
    HttpClient httpClient;
    @Mock
    Mapper mapper;

    @Test
    public void test_getCurrencies_successfulResponseReceived_currenciesListReturned()
            throws IOException, InterruptedException {
        String testBody = """
                {
                  "rates": [
                    {
                      "sellRate": 0,
                      "sellIso": "string",
                      "sellCode": 0,
                      "buyRate": 0,
                      "buyIso": "string",
                      "buyCode": 0,
                      "quantity": 0,
                      "name": "string",
                      "date": "06.08.2019"
                    }
                  ]
                }
                """;
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.body()).thenReturn(testBody);
        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK_200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        CurrencyRates currencyRates = new CurrencyRates();
        currencyRates.setRates(List.of(new CurrencyRate()));
        when(mapper.map(any(AlphaCurrencyRates.class), any())).thenReturn(currencyRates);

        assertThat(client.getCurrencies().getRates(), hasSize(1));
    }

    @Test
    public void test_getCurrencies_errorResponseReceived_exceptionIsThrown() throws IOException, InterruptedException {
        String testBody = """
                {
                  "category": "BACK_ERROR",
                  "code": 666,
                  "message": "Ur ApI is BrOkEn ;("
                }
                """;
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.body()).thenReturn(testBody);
        when(httpResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR_500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        assertThrows(OpenAPIRequestException.class, () -> client.getCurrencies());
    }

    @Test
    public void test_getCurrencies_IOExceptionOccurs_throwsException() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(IOException.class);

        assertThrows(OpenAPIRequestException.class, () -> client.getCurrencies());
    }

    @Test
    public void test_getCurrencies_InterruptedExceptionOccurs_throwsException() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(InterruptedException.class);

        assertThrows(OpenAPIRequestException.class, () -> client.getCurrencies());
    }
}