package org.kowalsky.bankingapi.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dagger.Module;
import dagger.Provides;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.inject.Singleton;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Module
public class MongoModule {

    @Provides
    @Singleton
    public MongoClient provideMongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress("localhost", 27017))))
                        .credential(MongoCredential.createCredential("admin", "bankingApiDB", "admin".toCharArray()))
                        .build());
    }

    @Provides
    @Singleton
    public MongoDatabase provideDB(MongoClient mongoClient) {
        return mongoClient.getDatabase("bankingApiDB").withCodecRegistry(
                fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build())));
    }
}
