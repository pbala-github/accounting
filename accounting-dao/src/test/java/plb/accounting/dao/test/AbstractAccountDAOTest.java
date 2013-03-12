package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dao.impl.db4o.DB4OTransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 10/31/12 3:04 PM
 */
public abstract class AbstractAccountDAOTest extends AbstractDAOTest<AccountDAO>{

    @Override
    @Test
    public void searchByCriteria() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 102");

        List<Account> accounts = getDAO().searchAccounts(criteria);


        assertNotNull(accounts);

        assertEquals(1, accounts.size());
        assertEquals("Account name 102", accounts.get(0).getName());
    }

    @Override
    @Test
    public void getAll() {
        List<Account> accounts = getDAO().getAll(Account.class);

        assertNotNull(accounts);
        assertSame(10,accounts.size());

    }


    @Test
    @Override
    public void persist() {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.valueOf(-1));
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);

        Account stored = getDAO().saveOrUpdate(account);

        assertNotSame(0, stored.getId());

        Account found = getDAO().findById(Account.class,stored.getId());

        assertNotNull(found);

        assertEquals(found.getName(),account.getName());
    }

    @Test
    @Override
    public void findById() {
        Account account = getDAO().findById(Account.class,56);

        assertNotNull(account);
        assertTrue(account.getId().equals(new Long(56)));
    }

     @Test
    @Override
    public void delete() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 101");
        List<Account> accounts = getDAO().searchAccounts(criteria);

        assertNotNull(accounts);
        assertEquals(1,accounts.size());

         int transacitons = accounts.get(0).getInTransactions().size();
         TransactionDAO transactionDAO = new DB4OTransactionDAO();
         int totalTransactions = transactionDAO.getAll(Account.class).size();

        getDAO().delete(Account.class,accounts.get(0).getId());

        accounts = getDAO().searchAccounts(criteria);

        assertEquals(0,accounts.size());
         int finalTransactionsTotal = transactionDAO.getAll(Account.class).size();

         assertEquals(totalTransactions-transacitons,finalTransactionsTotal);

    }

    @Test
    @Override
    public void update() {
        
        Account account = getDAO().findById(Account.class,56l);

        assertNotNull(account);

        account.setName("Updated Account name");

        getDAO().saveOrUpdate(account);

        Account found = getDAO().findById(Account.class,56l);

        assertNotNull(found);

        assertEquals("Updated Account name",found.getName());

    }



}
