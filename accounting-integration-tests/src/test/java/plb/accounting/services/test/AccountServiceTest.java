package plb.accounting.services.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.services.impl.intercept.Validate;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InterceptionType;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 3/17/13 11:26 PM
 */
@RunWith(JeeunitRunner.class)
public class AccountServiceTest extends AbstractServiceTest {

    @Inject
    BeanManager beanManager;

//    @Test
    public void getAccounts() {
        assertNotNull(service.getAccountsTree());
    }

//    @Test
    public void loadAccountById() {
        BaseAccountInfoDTO account = service.getAccountsTree().get(0);
        assertNotNull(account);

        assertEquals(account.getId(), service.findAccountById(account.getId()).getId());
    }

    @Test
    public void testBeanManager() {
        System.out.println("Interceptor binding type: " + beanManager.isInterceptorBinding(Validate.class));

        System.out.println(beanManager.getInterceptorBindingDefinition(Validate.class));
        System.out.println(beanManager.resolveInterceptors(InterceptionType.AROUND_INVOKE, new Validate(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return Validate.class;
            }

            @Override
            public Class<?>[] groups() {
                return new Class<?>[0];  //To change body of implemented methods use File | Settings | File Templates.
            }
        }));

    }

    @Test
    public void saveAccount() {
        AccountDTO account = new AccountDTO();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ZERO);
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);

        BaseAccountInfoDTO stored = service.saveAccount(account);
        assertNotSame(0, stored.getId());

        DetailedAccountDTO found = service.findAccountById(stored.getId());

        assertNotNull(found);

        assertEquals(found.getName(), account.getName());
    }

//    @Test
    public void deleteAccount() {
        BaseAccountInfoDTO account = service.getAccountsTree().get(0);
        assertNotNull(account);
        System.out.println("Found account: " + account);
        service.deleteAccount(account.getId());
        assertNull(service.findAccountById(account.getId()));
    }

//    @Test
    public void searchAccounts() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountName("Account name 102");
        List<BaseAccountInfoDTO> accounts = service.searchAccounts(criteria);
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
