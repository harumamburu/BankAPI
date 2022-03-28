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

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Module
public class MongoModule {

    @Provides
    @Singleton
    @Inject
    public MongoClient provideMongoClient(@Named("mongo.host") String mongoHost,
                                          @Named("mongo.port") String mongoPort,
                                          @Named("mongo.database") String db,
                                          @Named("mongo.user.name") String user,
                                          @Named("mongo.user.password") String pwd) {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress(mongoHost, Integer.parseInt(mongoPort)))))
                        .credential(MongoCredential.createCredential(user, db, pwd.toCharArray()))
                        .build());
    }

    @Provides
    @Singleton
    public MongoDatabase provideDB(MongoClient mongoClient, @Named("mongo.database") String db) {
        return mongoClient.getDatabase(db).withCodecRegistry(
                fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build())));
    }
}
