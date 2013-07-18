package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.AccountView;
import plb.accounting.model.view.ExternalPartyView;
import plb.accounting.model.view.TransactionView;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 11/2/12 2:30 PM
 */
public abstract class AbstractTransactionDAOTest extends AbstractDAOTest<TransactionDAO> {

    @EJB
    private AccountDAO accountDAO;

    @EJB
    private ExternalPartyDAO externalPartyDAO;


    @Test
    @Override
    public void persist() {
        beginTransaction();
        AccountView destinationAccountView = accountDAO.getAll().get(0);
        AccountView originAccountView = accountDAO.getAll().get(1);
        ExternalPartyView externalPartyView = externalPartyDAO.getAll().get(0);
        Account destinationAccount = getDAO().findById(Account.class, destinationAccountView.getDbId());
        Account originAccount = getDAO().findById(Account.class, originAccountView.getDbId());
        ExternalParty externalParty = getDAO().findById(ExternalParty.class, externalPartyView.getDbId());

        Transaction transaction = new Transaction(originAccount, destinationAccount, new Date(), BigDecimal.TEN, "transaction description");
        transaction.setRelatedParty(externalParty);

        transaction = getDAO().saveOrUpdate(transaction);
        commitTransaction();
        assertNotNull(transaction.getId());
    }

    @Test
    @Override
    public void findById() {
        TransactionView transaction = getDAO().getAll().get(0);
        assertNotNull(transaction);
        assertEquals(transaction.getDbId(), getDAO().findById(Transaction.class, transaction.getDbId()).getId());
    }

    @Test
    @Override
    public void delete() {
        beginTransaction();
        TransactionView transaction = getDAO().getAll().get(0);
        assertNotNull(transaction);
        getDAO().delete(Transaction.class, transaction.getDbId());
        commitTransaction();
        assertNull(getDAO().findById(Transaction.class, transaction.getDbId()));
    }

    @Test
    @Override
    public void update() {
        beginTransaction();
        TransactionView transactionView = getDAO().getAll().get(0);
        assertNotNull(transactionView);
        Transaction transaction = getDAO().findById(Transaction.class, transactionView.getDbId());
        transaction.setDescription("hhhhhhhhhhh");
        getDAO().saveOrUpdate(transaction);
        commitTransaction();
        transaction = getDAO().findById(Transaction.class, transaction.getId());
        assertEquals("hhhhhhhhhhh", transaction.getDescription());
    }

    @Test
    @Override
    public void getAll() {
        List<TransactionView> transactionViews = getDAO().getAll();
        assertNotNull(transactionViews);
        assertEquals(DataBootstrap.MAX_TRANSACTIONS, transactionViews.size());
    }

    @Test
    @Override
    public void searchByCriteria() {
        TransactionSearchCriteria criteria = new TransactionSearchCriteria();
        criteria.setDescription("tr_description_4");
        List<TransactionView> transactions = getDAO().searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());

        criteria.setAmountFrom(new BigDecimal(0));
        transactions = getDAO().searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());
        criteria.setAmountFrom(new BigDecimal(101));
        criteria.setAmountTo(new BigDecimal(109));
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
        assertEquals("org_name_3", transactions.get(0).getRelatedPartyName());

        criteria = new TransactionSearchCriteria();
        criteria.setOriginAccountIds(new HashSet<Long>(Arrays.asList(1l, 2l, 3l)));
        assertNotNull(getDAO().searchTransactions(criteria));
    }
}
