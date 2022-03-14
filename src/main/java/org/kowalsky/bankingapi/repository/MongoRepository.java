package org.kowalsky.bankingapi.repository;

import com.mongodb.client.MongoDatabase;
import org.kowalsky.bankingapi.model.BankingApiMeta;

import javax.inject.Inject;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository {

    private final MongoDatabase bankingDb;

    @Inject
    public MongoRepository(MongoDatabase bankingDb) {
        this.bankingDb = bankingDb;
    }

    public BankingApiMeta getApiMetaInfo(String bankCode) {
        return bankingDb.getCollection("bankingAPIs")
                .find(eq("bankCode", bankCode), BankingApiMeta.class).first();
    }
}
