package org.kowalsky.bankingapi.client;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class ClientBindingModule {

    @Binds
    @IntoSet
    abstract CurrenciesClient bindAlphaClient(AlphaClient alphaClient);
}
