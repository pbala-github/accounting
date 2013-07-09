package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 11/2/12 2:30 PM
 */
public abstract class AbstractTransactionDAOTest extends AbstractDAOTest<TransactionDAO> {

    @Inject
    DataBootstrap dataBootstrap;

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
        assertEquals(transaction.getId(), getDAO().findById(Transaction.class, transaction.getId()).getId());
    }

    @Test
    @Override
    public void delete() {
        Transaction transaction = getDAO().getAll(Transaction.class).get(0);
        assertNotNull(transaction);
        getDAO().delete(Transaction.class, transaction.getId());
        assertNull(getDAO().findById(Transaction.class, transaction.getId()));
    }

    @Test
    @Override
    public void update() {
        Transaction transaction = getDAO().getAll(Transaction.class).get(0);
        assertNotNull(transaction);
        transaction.setDescription("hhhhhhhhhhh");
        getDAO().saveOrUpdate(transaction);
        transaction = getDAO().findById(Transaction.class, transaction.getId());
        assertEquals("hhhhhhhhhhh", transaction.getDescription());
    }

    @Test
    @Override
    public void getAll() {
        assertNotNull(getDAO().getAll(Transaction.class));
    }

    @Test
    @Override
    public void searchByCriteria() {
        TransactionSearchCriteria criteria = new TransactionSearchCriteria();
        criteria.setDescription("tr_description_4");
        List<Transaction> transactions = getDAO().searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());

        criteria.setAmountFrom(new BigDecimal(5));
        transactions = getDAO().searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());

        criteria.setAmountTo(new BigDecimal(10));
        transactions = getDAO().searchTransactions(criteria);
        assertEquals(0, transactions.size());

        criteria = new TransactionSearchCriteria();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        criteria.setExecutionDateFrom(calendar.getTime());
        transactions = getDAO().searchTransactions(criteria);
        assertFalse(transactions.isEmpty());

        calendar.set(Calendar.MONTH, 2);
        criteria.setExecutionDateTo(calendar.getTime());
        transactions = getDAO().searchTransactions(criteria);
        assertEquals(0, transactions.size());

        criteria = new TransactionSearchCriteria();
        criteria.setOrgName("org_name_3");
        transactions = getDAO().searchTransactions(criteria);
        assertFalse(transactions.isEmpty());
        assertEquals("org_name_3", transactions.get(0).getRelatedParty().getName());

        criteria = new TransactionSearchCriteria();
        criteria.setOriginAccountIds(new HashSet<Long>(Arrays.asList(1l, 2l, 3l)));
        assertNotNull(getDAO().searchTransactions(criteria));
    }
}
