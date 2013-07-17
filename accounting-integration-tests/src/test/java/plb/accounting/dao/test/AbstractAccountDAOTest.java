package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.AccountView;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 10/31/12 3:04 PM
 */
public abstract class AbstractAccountDAOTest extends AbstractDAOTest<AccountDAO> {


    @Override
    @Test
    public void searchByCriteria() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 1");
        List<AccountView> accounts = getDAO().searchAccounts(criteria);
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals("Account name 1", accounts.get(0).getName());

        criteria.setLowestAccountBalance(new BigDecimal(100000));
        accounts = getDAO().searchAccounts(criteria);
        assertNotNull(accounts);
        assertEquals(0, accounts.size());

        criteria = new AccountSearchCriteria();
        criteria.setAccountType(plb.accounting.dto.AccountTypeEnum.INCOME);
        accounts = getDAO().searchAccounts(criteria);
        assertTrue(accounts.isEmpty());

        criteria.setAccountType(plb.accounting.dto.AccountTypeEnum.OUTCOME);
        accounts = getDAO().searchAccounts(criteria);
        assertFalse(accounts.isEmpty());
    }

    @Override
    @Test
    public void getAll() {
        List<AccountView> accountViews = getDAO().getAll();
        assertNotNull(accountViews);
        assertEquals(DataBootstrap.MAX_ACCOUNTS,accountViews.size());
    }


    @Test
    @Override
    public void persist() {
        Account account = new Account("Account name", AccountTypeEnum.OUTCOME, BigDecimal.ZERO);
        account.setDescription("Description");
        beginTransaction();
        Account stored = getDAO().saveOrUpdate(account);
        commitTransaction();
        assertNotSame(0, stored.getId());

        Account found = getDAO().findById(Account.class, stored.getId());

        assertNotNull(found);
        assertEquals(found.getName(), account.getName());
    }

    @Test
    @Override
    public void findById() {
        AccountView account = getDAO().getAll().get(0);
        assertNotNull(account);

        assertEquals(account.getDbId(), getDAO().findById(Account.class, account.getDbId()).getId());
    }

    @Test
    @Override
    public void update() {
        AccountView accountView = getDAO().getAll().get(0);
        assertNotNull(accountView);
        Account account = getDAO().findById(Account.class, accountView.getDbId());

        account.setName("Updated Account name");
        beginTransaction();
        getDAO().saveOrUpdate(account);
        commitTransaction();
        Account found = getDAO().findById(Account.class, account.getId());
        assertNotNull(found);
        assertEquals("Updated Account name", found.getName());
    }

    @Test
    @Override
    public void delete() {
        beginTransaction();
        AccountView accountView = getDAO().getAll().get(0);
        assertNotNull(accountView);
        getDAO().delete(Account.class, accountView.getDbId());
        commitTransaction();
        assertNull(getDAO().findById(Account.class, accountView.getDbId()));
    }

}
