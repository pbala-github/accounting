import org.junit.Before;
import org.junit.Test;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author pbala
 */
public class TransactionTest {

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
    public void testConstructionNullOriginAccount() {
        new Transaction(null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullDestinationAccount() {
        new Transaction(originAccount, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionSameOriginDestinationAccount() {
        new Transaction(originAccount, originAccount, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullExecutionDate() {
        new Transaction(originAccount, destinationAccount, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullAmount() {
        new Transaction(originAccount, destinationAccount, new Date(), null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNegativeAmount() {
        new Transaction(originAccount, destinationAccount, new Date(), new BigDecimal(-45), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNotEnoughMoney() {
        new Transaction(originAccount, destinationAccount, new Date(), new BigDecimal(200045), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullDescription() {
        new Transaction(originAccount, destinationAccount, new Date(), new BigDecimal(45), null);
    }

    @Test
    public void testConstruction() {
        BigDecimal ocb1 = originAccount.getCurrentBalance();
        BigDecimal dcb1 = destinationAccount.getCurrentBalance();
        Transaction transaction = getTransaction();
        BigDecimal amount = transaction.getAmount();
        assertSame(originAccount, transaction.getOriginAccount());
        assertSame(destinationAccount, transaction.getDestinationAccount());
        assertEquals(ocb1, originAccount.getCurrentBalance().add(amount));
        assertEquals(dcb1, destinationAccount.getCurrentBalance().subtract(amount));
    }
    //*********************************************************
    //
    //
    //Test behaviour

    @Test
    public void testDiscard() {
        BigDecimal ocb1 = originAccount.getCurrentBalance();
        BigDecimal dcb1 = destinationAccount.getCurrentBalance();

        //create a transaction, amounts are altered
        Transaction transaction = getTransaction();

        assertFalse(ocb1.equals(originAccount.getCurrentBalance()));
        assertFalse(dcb1.equals(destinationAccount.getCurrentBalance()));

        transaction.discard();

        assertSame(null, transaction.getOriginAccount());
        assertSame(null, transaction.getDestinationAccount());
        assertEquals(ocb1, originAccount.getCurrentBalance());
        assertEquals(dcb1, destinationAccount.getCurrentBalance());
        assertEquals(0, originAccount.getOutTransactions().size());
        assertEquals(0, destinationAccount.getInTransactions().size());
    }

    @Test
    public void testAmountUpdate() {
        BigDecimal ocb1 = originAccount.getCurrentBalance();
        BigDecimal dcb1 = destinationAccount.getCurrentBalance();

        //create a transaction, amounts are altered
        Transaction transaction = getTransaction();
        BigDecimal initialAmount = transaction.getAmount();

        assertEquals(ocb1, initialAmount.add(originAccount.getCurrentBalance()));
        assertEquals(dcb1, destinationAccount.getCurrentBalance().subtract(initialAmount));

        BigDecimal newAmount = new BigDecimal(50);
        transaction.setAmount(newAmount);
        assertEquals(ocb1, newAmount.add(originAccount.getCurrentBalance()));
        assertEquals(dcb1, destinationAccount.getCurrentBalance().subtract(newAmount));
    }

    @Test
    public void testOriginAccountUpdate() {
        BigDecimal ocb1 = originAccount.getCurrentBalance();

        //create a transaction, amounts are altered
        Transaction transaction = getTransaction();
        BigDecimal amount = transaction.getAmount();
        Account newOriginAccount = SampleFactory.getOriginAccount();
        BigDecimal nocb1 = newOriginAccount.getCurrentBalance();

        transaction.setOriginAccount(newOriginAccount);

        assertEquals(ocb1, originAccount.getCurrentBalance());
        assertEquals(nocb1, amount.add(newOriginAccount.getCurrentBalance()));
    }

    @Test
    public void testExternalPartyUpdate() {
        Transaction transaction = getTransaction();
        int initialSize = externalParty.getTransactions().size();
        assertEquals(1, initialSize);

        ExternalParty externalParty2 = SampleFactory.getExternalParty();
        int size2 = externalParty2.getTransactions().size();
        assertEquals(0, size2);

        transaction.setRelatedParty(externalParty2);
        int updatedSize = externalParty.getTransactions().size();
        int updatedSize2 = externalParty2.getTransactions().size();

        assertEquals(0, updatedSize);
        assertEquals(1, updatedSize2);
    }

    @Test
    public void testDestinationAccountUpdate() {
        BigDecimal dcb1 = destinationAccount.getCurrentBalance();

        //create a transaction, amounts are altered
        Transaction transaction = getTransaction();
        BigDecimal amount = transaction.getAmount();
        Account newDestinationAccount = SampleFactory.getDestinationAccount();
        BigDecimal ndcb1 = newDestinationAccount.getCurrentBalance();

        transaction.setDestinationAccount(newDestinationAccount);

        assertEquals(dcb1, destinationAccount.getCurrentBalance());
        assertEquals(ndcb1, newDestinationAccount.getCurrentBalance().subtract(amount));
    }



    //********************** helper methods********************
    private Transaction getTransaction() {
        BigDecimal amount = new BigDecimal(45);
        Transaction transaction = new Transaction(originAccount, destinationAccount, new Date(), amount, "test transaction 1");
        transaction.setRelatedParty(externalParty);
        return transaction;
    }
}
