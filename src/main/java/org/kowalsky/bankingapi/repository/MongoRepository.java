package org.kowalsky.bankingapi.repository;

import com.mongodb.client.MongoDatabase;
import org.kowalsky.bankingapi.model.BankingApiMeta;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    public List<BankingApiMeta> getAllApiMetaInfo() {
        List<BankingApiMeta> apiMetadata = new ArrayList<>();
        bankingDb.getCollection("bankingAPIs").find(BankingApiMeta.class).cursor()
                .forEachRemaining(apiMetadata::add);
        return apiMetadata;
    }
}
