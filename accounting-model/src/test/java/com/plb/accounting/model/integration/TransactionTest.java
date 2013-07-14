package com.plb.accounting.model.integration;

import com.plb.accounting.model.SampleFactory;
import org.junit.Assert;
import org.junit.Test;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

/**
 * @author pbala
 */
public class TransactionTest extends BaseIntegrationTest {

    @Test
    public void testPersistTransaction() {
        Account originAccount = SampleFactory.getOriginAccount();
        Account destinationAccount = SampleFactory.getDestinationAccount();
        ExternalParty externalParty = SampleFactory.getExternalParty();
        entityTransaction.begin();
        entityManager.persist(originAccount);
        entityManager.persist(destinationAccount);
        entityManager.persist(externalParty);
        Transaction transaction = SampleFactory.getTransaction(originAccount, destinationAccount);
        transaction.setRelatedParty(externalParty);
        entityManager.persist(transaction);
        entityTransaction.commit();
        Assert.assertNotNull(transaction.getId());

    }
}
