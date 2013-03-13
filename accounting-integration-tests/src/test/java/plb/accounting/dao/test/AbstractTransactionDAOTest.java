package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * User: pbala
 * Date: 11/2/12 2:30 PM
 */
public abstract class AbstractTransactionDAOTest extends AbstractDAOTest<TransactionDAO>{

    @Test
    @Override
    public void persist() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.ZERO);
        transaction.setDescription("transaction description");
        Account destinationAccount = getDAO().getAll(Account.class).get(0);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setExecutionDate(new Date());
        Account originAccount = getDAO().getAll(Account.class).get(1);
        transaction.setOriginAccount(originAccount);
        ExternalParty externalParty = getDAO().getAll(ExternalParty.class).get(0);
        transaction.setRelatedParty(externalParty);

        transaction = getDAO().saveOrUpdate(transaction);
        assertNotNull(transaction.getId());
    }

    @Test
    @Override
    public void findById() {
        Transaction transaction = getDAO().getAll(Transaction.class).get(0);
        assertNotNull(transaction);
        assertEquals(transaction.getId(), getDAO().findById(Transaction.class,transaction.getId()).getId());
    }

    @Test
    @Override
    public void delete() {
        Transaction transaction = getDAO().getAll(Transaction.class).get(0);
        assertNotNull(transaction);
        getDAO().delete(Transaction.class,transaction.getId());
        assertNull(getDAO().findById(Transaction.class, transaction.getId()));
    }

    @Test
    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Test
    @Override
    public void getAll() {
        assertNotNull(getDAO().getAll(Transaction.class));
    }

//    @Test
    @Override
    public void searchByCriteria() {
        TransactionSearchCriteria criteria = new TransactionSearchCriteria();

        criteria.setDescription("tr_description_1");

        List<Transaction> transactions = getDAO().searchTransactions(criteria);

        assertEquals(1,transactions.size());

        assertEquals("tr_description_1",transactions.get(0).getDescription());

    }
}
