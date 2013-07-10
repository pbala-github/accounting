package com.plb.accounting.model.tests;

import org.junit.Before;
import org.junit.Test;
import plb.accounting.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author pbala
 */
public class AccountTest {

    private Account originAccount;

    private Account destinationAccount;

    private ExternalParty externalParty;

    @Before
    public void globalInit() {
        originAccount = SampleFactory.getOriginAccount();
        destinationAccount = SampleFactory.getDestinationAccount();
        externalParty = SampleFactory.getExternalParty();
    }

    //Construction tests
    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullName() {
        new Account(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompositeConstructionNullName() {
        new AccountComposite(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionEmptyName() {
        new Account("", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompositeConstructionEmptyName() {
        new AccountComposite("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullType() {
        new Account("account 1", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompositeConstructionNullType() {
        new AccountComposite("account 1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullInitialBalance() {
        new Account("account 1", AccountTypeEnum.OUTCOME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNegativeInitialBalance() {
        new Account("account 1", AccountTypeEnum.OUTCOME, new BigDecimal(-56));
    }

    @Test
    public void testConstruction() {
        Account account = new Account("account 1", AccountTypeEnum.OUTCOME, new BigDecimal(560));
        assertNotNull(account);
        assertEquals("account 1", account.getName());
        assertEquals(AccountTypeEnum.OUTCOME, account.getType());
        assertEquals(new BigDecimal(560), account.getInitialBalance());
        assertEquals(new BigDecimal(560), account.getCurrentBalance());
    }

    @Test
    public void testCompositeConstruction() {
        IAccount account = new AccountComposite("account 1", AccountTypeEnum.OUTCOME);
        assertNotNull(account);
        assertEquals("account 1", account.getName());
        assertEquals(AccountTypeEnum.OUTCOME, account.getType());
        assertEquals(new BigDecimal(0), account.getInitialBalance());
        assertEquals(new BigDecimal(0), account.getCurrentBalance());
    }
    //*************************************************
    //
    // Behavioural tests
    //

    @Test
    public void testGetTransactions() {
        assertEquals(0, originAccount.getOutTransactions().size());
        assertEquals(0, destinationAccount.getInTransactions().size());
        assertEquals(0, destinationAccount.getOutTransactions().size());
        assertEquals(0, originAccount.getInTransactions().size());

        Transaction transaction = getTransaction();

        assertEquals(1, originAccount.getOutTransactions().size());
        assertEquals(1, destinationAccount.getInTransactions().size());
        assertEquals(0, destinationAccount.getOutTransactions().size());
        assertEquals(0, originAccount.getInTransactions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompositeAddInvalidTypeChildrenAccount() {
        IAccount account = new AccountComposite("account 1", AccountTypeEnum.OUTCOME);
        account.addChildrenAccount(originAccount);
    }

    @Test
    public void testCompositeAddValidTypeChildrenAccount() {
        //Outcome type account
        IAccount account = new AccountComposite("account 1", AccountTypeEnum.OUTCOME);
        BigDecimal icb = account.getCurrentBalance();
        BigDecimal iib = account.getInitialBalance();
        assertEquals(0, account.getChildrenAccounts().size());
        assertSame(null, destinationAccount.getParentAccount());
        assertEquals(BigDecimal.ZERO, icb);
        assertEquals(BigDecimal.ZERO, iib);

        getTransaction();
        account.addChildrenAccount(destinationAccount);

        assertEquals(1, account.getChildrenAccounts().size());
        assertSame(account, destinationAccount.getParentAccount());
        assertEquals(account.getCurrentBalance(), icb.add(destinationAccount.getCurrentBalance()));
        assertEquals(account.getInitialBalance(), iib.add(destinationAccount.getInitialBalance()));

        //Storage type accounts
        account = new AccountComposite("account 1", AccountTypeEnum.STORAGE);
        icb = account.getCurrentBalance();
        iib = account.getInitialBalance();
        assertEquals(0, account.getChildrenAccounts().size());
        assertSame(null, originAccount.getParentAccount());
        assertEquals(BigDecimal.ZERO, icb);
        assertEquals(BigDecimal.ZERO, iib);

        account.addChildrenAccount(originAccount);

        assertEquals(1, account.getChildrenAccounts().size());
        assertSame(account, originAccount.getParentAccount());
        assertEquals(account.getCurrentBalance(), icb.add(originAccount.getCurrentBalance()));
        assertEquals(account.getInitialBalance(), iib.add(originAccount.getInitialBalance()));
    }

    @Test
    public void testCompositeAddAllChildrenAccounts() {
        IAccount account = new AccountComposite("account 1", AccountTypeEnum.OUTCOME);
        BigDecimal icb = account.getCurrentBalance();
        BigDecimal iib = account.getInitialBalance();
        assertEquals(0, account.getChildrenAccounts().size());
        assertEquals(BigDecimal.ZERO, icb);
        assertEquals(BigDecimal.ZERO, iib);

        getTransaction();
        Account destinationAccount1 = SampleFactory.getDestinationAccount();
        account.addAllChildrenAccount(Arrays.asList(destinationAccount,destinationAccount1));

        new Transaction(originAccount, destinationAccount1, new Date(), new BigDecimal(15), "test transaction 1");

        assertEquals(2, account.getChildrenAccounts().size());
        assertSame(account, destinationAccount.getParentAccount());
        assertSame(account, destinationAccount1.getParentAccount());
        assertEquals(account.getCurrentBalance(), icb.add(destinationAccount.getCurrentBalance()).add(destinationAccount1.getCurrentBalance()));
        assertEquals(account.getInitialBalance(), iib.add(destinationAccount.getInitialBalance()).add(destinationAccount1.getInitialBalance()));
    }

    @Test
    public void testCompositeRemoveChildrenAccount() {
        //Outcome type account
        IAccount account = new AccountComposite("account 1", AccountTypeEnum.OUTCOME);
        BigDecimal icb = account.getCurrentBalance();
        BigDecimal iib = account.getInitialBalance();
        assertEquals(0, account.getChildrenAccounts().size());
        assertSame(null, destinationAccount.getParentAccount());
        assertEquals(BigDecimal.ZERO, icb);
        assertEquals(BigDecimal.ZERO, iib);

        getTransaction();
        account.addChildrenAccount(destinationAccount);

        assertEquals(1, account.getChildrenAccounts().size());
        assertSame(account, destinationAccount.getParentAccount());
        assertEquals(account.getCurrentBalance(), icb.add(destinationAccount.getCurrentBalance()));
        assertEquals(account.getInitialBalance(), iib.add(destinationAccount.getInitialBalance()));

        account.removeChildrenAccount(destinationAccount);
        assertEquals(0, account.getChildrenAccounts().size());
        assertSame(null, destinationAccount.getParentAccount());
        assertEquals(BigDecimal.ZERO, account.getCurrentBalance());
        assertEquals(BigDecimal.ZERO, account.getInitialBalance());
    }

    //********************** helper methods********************
    private Transaction getTransaction() {
        BigDecimal amount = new BigDecimal(45);
        Transaction transaction = new Transaction(originAccount, destinationAccount, new Date(), amount, "test transaction 1");
        transaction.setRelatedParty(externalParty);
        return transaction;
    }
}
