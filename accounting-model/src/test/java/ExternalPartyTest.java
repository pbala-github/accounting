import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author pbala
 */
public class ExternalPartyTest {

    private List<Transaction> transactions;

    private ExternalParty externalParty;

    private static Account originAccount;

    private static Account destinationAccount;

    @BeforeClass
    public static void globalInit() {
        originAccount = new Account("origin account 1", AccountTypeEnum.STORAGE, new BigDecimal(2000));
        destinationAccount = new Account("destination account 1", AccountTypeEnum.OUTCOME, new BigDecimal(0));
    }

    @Before
    public void initExternalParty() {
        externalParty = new ExternalParty("external party");

        transactions = Arrays.asList(new Transaction(//
                originAccount,//
                destinationAccount,//
                Calendar.getInstance().getTime(),//
                new BigDecimal(45.2),//
                "sample transaction 1"),//
                new Transaction(//
                        originAccount,//
                        destinationAccount,//
                        Calendar.getInstance().getTime(),//
                        new BigDecimal(59),//
                        "sample transaction 2"),//
                new Transaction(//
                        originAccount,//
                        destinationAccount,//
                        Calendar.getInstance().getTime(),//
                        new BigDecimal(345),//
                        "sample transaction 3")
        );
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUpdateNull() {
        externalParty.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUpdateEmpty() {
        externalParty.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionNull() {
        externalParty.addTransaction(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionOfOtherExternalParty() {
        transactions.get(0).setRelatedParty(new ExternalParty("external party 2"));
        externalParty.addTransaction(transactions.get(0));
    }

    @Test
    public void testAddTransaction() {
        assertTrue(externalParty.addTransaction(transactions.get(0)));
        assertTrue(transactions.get(0).getRelatedParty() == externalParty);
        assertEquals(1, externalParty.getTransactions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllTransactionsNull() {
        externalParty.addAllTransactions(null);
    }

    @Test
    public void testAddAllTransactions() {
        assertTrue(externalParty.addAllTransactions(transactions));
        for (Transaction transaction : transactions) {
            assertTrue(transaction.getRelatedParty() == externalParty);
        }
        assertEquals(transactions.size(), externalParty.getTransactions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionNull() {
        externalParty.removeTransaction(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionUnknown() {
        externalParty.addTransaction(transactions.get(0));
        externalParty.removeTransaction(transactions.get(1));

    }

    @Test
    public void testRemoveTransaction() {
        externalParty.addTransaction(transactions.get(0));
        assertTrue(externalParty.removeTransaction(transactions.get(0)));
        assertEquals(0, externalParty.getTransactions().size());
        assertSame(transactions.get(0).getRelatedParty(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAllTransactionsNull() {
        externalParty.removeAllTransactions(null);
    }

    @Test
    public void testRemoveAllTransactions() {
        externalParty.addAllTransactions(transactions);
        assertTrue(externalParty.removeAllTransactions(transactions));
        assertEquals(0, externalParty.getTransactions().size());
        for (Transaction transaction : transactions) {
            assertTrue(transaction.getRelatedParty() == null);
        }
    }

}
