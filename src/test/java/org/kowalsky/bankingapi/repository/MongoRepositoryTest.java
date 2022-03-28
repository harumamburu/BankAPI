package org.kowalsky.bankingapi.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kowalsky.bankingapi.model.BankingApiMeta;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MongoRepositoryTest {

    @InjectMocks
    MongoRepository repo;

    @Mock
    MongoDatabase bankingDb;

    @Test
    public void test_getApiMetaInfo() {
        BankingApiMeta bankingApiMeta = new BankingApiMeta();
        FindIterable findMock = mock(FindIterable.class);
        when(findMock.first()).thenReturn(bankingApiMeta);
        MongoCollection collectionMock = mock(MongoCollection.class);
        when(collectionMock.find(any(Bson.class), eq(BankingApiMeta.class))).thenReturn(findMock);
        when(bankingDb.getCollection(anyString())).thenReturn(collectionMock);

        assertThat(repo.getApiMetaInfo(""), is(bankingApiMeta));
    }

    @Test
    public void test_getAllApiMetaInfo() {
        BankingApiMeta bankingApiMeta = new BankingApiMeta();
        MongoCursor cursorMock = mock(MongoCursor.class);
        when(cursorMock.hasNext()).thenReturn(true).thenReturn(false);
        when(cursorMock.next()).thenReturn(bankingApiMeta);
        doAnswer(invocation -> invocation.callRealMethod()).when(cursorMock).forEachRemaining(any());
        FindIterable findMock = mock(FindIterable.class);
        when(findMock.cursor()).thenReturn(cursorMock);
        MongoCollection collectionMock = mock(MongoCollection.class);
        when(collectionMock.find(eq(BankingApiMeta.class))).thenReturn(findMock);
        when(bankingDb.getCollection(anyString())).thenReturn(collectionMock);

        List<BankingApiMeta> allApiMetaInfo = repo.getAllApiMetaInfo();
        assertThat(allApiMetaInfo, hasSize(1));
        assertThat(allApiMetaInfo.get(0), is(bankingApiMeta));
    }
}