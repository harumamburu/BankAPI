package org.kowalsky.bankingapi.clinet;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.net.http.HttpClient;

@Module
public class HttpClientModule {

    @Provides
    @Named("alphaHttpClient")
    public HttpClient provideAlphaHttpClient() {
        return HttpClient.newBuilder().build();
    }
}
