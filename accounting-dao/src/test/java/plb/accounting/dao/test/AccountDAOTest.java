package plb.accounting.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountDAO;
import plb.accounting.dao.ITransactionDAO;
import plb.accounting.dao.impl.db4o.DB4OTransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 3:04 PM
 */
public abstract class AccountDAOTest extends AbstractDAOTest<IAccountDAO>{

    @Override
//    @Test
    public void searchByCriteria() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 102");

        List<Account> accounts = getDAO().searchAccounts(criteria);


        assertNotNull(accounts);

        assertEquals(1, accounts.size());
        assertEquals("Account name 102", accounts.get(0).getName());
    }

    @Override
//    @Test
    public void getAll() {
        List<Account> accounts = getDAO().getAll();

        assertNotNull(accounts);
        assertSame(9,accounts.size());

    }


//    @Test
    @Override
    public void persist() {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ONE);
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);

        Account stored = getDAO().persist(account);

        assertNotSame(0, stored.getId());

        Account found = getDAO().findById(stored.getId());

        assertNotNull(found);

        assertEquals(found.getName(),account.getName());
    }

//    @Test
    @Override
    public void findById() {
        Account account = getDAO().findById(56);

        assertNotNull(account);
        assertEquals(account.getId(),56l);
    }

     @Test
    @Override
    public void delete() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 103");
        List<Account> accounts = getDAO().searchAccounts(criteria);

        assertNotNull(accounts);
        assertEquals(1,accounts.size());

         int transacitons = accounts.get(0).getTransactions().size();
         ITransactionDAO transactionDAO = new DB4OTransactionDAO();
         int totalTransactions = transactionDAO.getAll().size();

        getDAO().delete(accounts.get(0).getId());

        accounts = getDAO().searchAccounts(criteria);

        assertEquals(0,accounts.size());
         int finalTransactionsTotal = transactionDAO.getAll().size();

         assertEquals(totalTransactions-transacitons,finalTransactionsTotal);

    }

//    @Test
    @Override
    public void update() {
        
        Account account = getDAO().findById(56l);

        assertNotNull(account);

        account.setName("Updated Account name");

        getDAO().update(account);

        Account found = getDAO().findById(56l);

        assertNotNull(found);

        assertEquals("Updated Account name",found.getName());

    }



}
