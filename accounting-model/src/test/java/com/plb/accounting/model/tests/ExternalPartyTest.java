package com.plb.accounting.model.tests;

import org.junit.Before;
import org.junit.Test;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author pbala
 */
public class ExternalPartyTest {

    private List<Transaction> transactions;

    private ExternalParty externalParty;

    private Account originAccount;

    private Account destinationAccount;

    @Before
    public void initExternalParty() {
        externalParty = new ExternalParty("external party");
        originAccount = SampleFactory.getOriginAccount();
        destinationAccount = SampleFactory.getDestinationAccount();

        transactions = new ArrayList<>();
        Transaction transaction = new Transaction(//
                originAccount,//
                destinationAccount,//
                Calendar.getInstance().getTime(),//
                new BigDecimal(45.2),//
                "sample transaction 1");
        transaction.setRelatedParty(externalParty);
        transactions.add(transaction);

        transaction =
                new Transaction(//
                        originAccount,//
                        destinationAccount,//
                        Calendar.getInstance().getTime(),//
                        new BigDecimal(59),//
                        "sample transaction 2");
        transaction.setRelatedParty(externalParty);
        transactions.add(transaction);

        transaction =
                new Transaction(//
                        originAccount,//
                        destinationAccount,//
                        Calendar.getInstance().getTime(),//
                        new BigDecimal(345),//
                        "sample transaction 3");
        transaction.setRelatedParty(externalParty);
        transactions.add(transaction);
    }

    //Construction tests
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructionNull() {
        new ExternalParty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructionEmpty() {
        new ExternalParty("");
    }

    @Test
    public void testValidConstruction() {
        new ExternalParty("external party 1");
    }

    //Behavioural tests
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUpdateNull() {
        externalParty.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUpdateEmpty() {
        externalParty.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionNull() {
        externalParty.removeTransaction(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionUnknown() {
        Transaction transaction = new Transaction(//
                originAccount,//
                destinationAccount,//
                Calendar.getInstance().getTime(),//
                new BigDecimal(45.2),//
                "sample transaction 1");
        externalParty.removeTransaction(transaction);

    }

    @Test
    public void testRemoveTransaction() {
        int initialSize = externalParty.getTransactions().size();
        assertTrue(externalParty.removeTransaction(transactions.get(0)));
        assertEquals(initialSize-1, externalParty.getTransactions().size());
        assertSame(transactions.get(0).getRelatedParty(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAllTransactionsNull() {
        externalParty.removeAllTransactions(null);
    }

    @Test
    public void testRemoveAllTransactions() {
        assertTrue(externalParty.removeAllTransactions(transactions));
        assertEquals(0, externalParty.getTransactions().size());
        for (Transaction transaction : transactions) {
            assertTrue(transaction.getRelatedParty() == null);
        }
    }

}
