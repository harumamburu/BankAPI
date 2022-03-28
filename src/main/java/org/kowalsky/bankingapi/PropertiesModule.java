package org.kowalsky.bankingapi;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.io.IOException;
import java.util.Properties;

@Module
public class PropertiesModule {

    private final Properties props;

    public PropertiesModule() {
        this.props = new Properties();
        try {
            this.props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Named("mongo.host")
    public String provideMongoHost() {
        return props.getProperty("mongo.host", "localhost");
    }

    @Provides
    @Named("mongo.port")
    public String provideMongoPort() {
        return props.getProperty("mongo.port", "27017");
    }

    @Provides
    @Named("mongo.database")
    public String provideMongoDatabase() {
        return props.getProperty("mongo.database", "bankingApiDB");
    }

    @Provides
    @Named("mongo.user.name")
    public String provideMongoUserName() {
        return props.getProperty("mongo.user.name", "admin");
    }

    @Provides
    @Named("mongo.user.password")
    public String provideMongoUserPassword() {
        return props.getProperty("mongo.user.password", "admin");
    }

    @Provides
    public Properties provideProperties() {
        return this.props;
    }
}
