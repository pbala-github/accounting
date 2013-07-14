package com.plb.accounting.model.integration;

import com.plb.accounting.model.SampleFactory;
import org.junit.*;
import plb.accounting.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author pbala
 */
public class AccountTest extends BaseIntegrationTest{


    @Test
    public void testPersistAccount() {
        Account originAccount = SampleFactory.getOriginAccount();
        entityTransaction.begin();
        entityManager.persist(originAccount);
        entityTransaction.commit();
        Assert.assertNotNull(originAccount.getId());
        Account destinationAccount = SampleFactory.getDestinationAccount();
        entityTransaction.begin();
        entityManager.persist(destinationAccount);
        entityTransaction.commit();
        Assert.assertNotNull(destinationAccount.getId());
    }


}
