package org.kowalsky.bankingapi.model.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @Provides
    public Mapper provideDefaultMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }
}
