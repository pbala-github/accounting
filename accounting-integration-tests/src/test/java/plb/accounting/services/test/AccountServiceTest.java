package plb.accounting.services.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.DetailedAccountDTO;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 3/17/13 11:26 PM
 */
@RunWith(JeeunitRunner.class)
public class AccountServiceTest extends AbstractServiceTest {

    @Test
    public void getAccounts() {
        assertNotNull(service.getAccounts());
    }

    @Test
    public void loadAccountById() {
        BaseAccountDTO account = service.getAccounts().get(0);
        assertNotNull(account);

        assertEquals(account.getId(), service.findAccountById(account.getId()).getId());
    }

    @Test
    public void saveAccount() {
        AccountDTO account = new AccountDTO();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ZERO);
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);

        BaseAccountDTO stored = service.saveAccount(account);
        assertNotSame(0, stored.getId());

        DetailedAccountDTO found = service.findAccountById(stored.getId());

        assertNotNull(found);

        assertEquals(found.getName(), account.getName());
    }

    @Test
    public void deleteAccount() {
        BaseAccountDTO account = service.getAccounts().get(0);
        assertNotNull(account);
        System.out.println("Found account: " + account);
        service.deleteAccount(account.getId());
        assertNull(service.findAccountById(account.getId()));
    }

    @Test
    public void searchAccounts() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 102");
        List<BaseAccountDTO> accounts = service.searchAccounts(criteria);
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals("Account name 102", accounts.get(0).getName());

        criteria.setLowestAccountBalance(new BigDecimal(5));
        accounts = service.searchAccounts(criteria);
        assertNotNull(accounts);
        assertEquals(0, accounts.size());

        criteria = new AccountSearchCriteria();
        criteria.setAccountType(plb.accounting.dto.AccountTypeEnum.INCOME);
        accounts = service.searchAccounts(criteria);
        assertTrue(accounts.isEmpty());

        criteria.setAccountType(plb.accounting.dto.AccountTypeEnum.OUTCOME);
        accounts = service.searchAccounts(criteria);
        assertFalse(accounts.isEmpty());
    }
}