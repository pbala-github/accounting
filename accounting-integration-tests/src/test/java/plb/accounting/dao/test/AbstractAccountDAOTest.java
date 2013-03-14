package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

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
        criteria.setAccountName("Account name 102");
        List<Account> accounts = getDAO().searchAccounts(criteria);
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals("Account name 102", accounts.get(0).getName());

        criteria.setLowestAccountBalance(new BigDecimal(5));
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
        assertNotNull(getDAO().getAll(Account.class));
    }


    @Test
    @Override
    public void persist() {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ZERO);
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);

        Account stored = getDAO().saveOrUpdate(account);
        assertNotSame(0, stored.getId());

        Account found = getDAO().findById(Account.class, stored.getId());

        assertNotNull(found);

        assertEquals(found.getName(), account.getName());
    }

    @Test
    @Override
    public void findById() {
        Account account = getDAO().getAll(Account.class).get(0);
        assertNotNull(account);
        ;

        assertEquals(account.getId(), getDAO().findById(Account.class, account.getId()).getId());
    }

    @Test
    @Override
    public void update() {
        Account account = getDAO().getAll(Account.class).get(0);
        assertNotNull(account);

        account.setName("Updated Account name");
        getDAO().saveOrUpdate(account);

        Account found = getDAO().findById(Account.class, account.getId());
        assertNotNull(found);
        assertEquals("Updated Account name", found.getName());
    }

    @Test
    @Override
    public void delete() {
        Account account = getDAO().getAll(Account.class).get(0);
        assertNotNull(account);
        getDAO().delete(Account.class, account.getId());
        assertNull(getDAO().findById(Account.class, account.getId()));

    }


}
