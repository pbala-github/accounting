package plb.accounting.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import plb.accounting.dao.IAccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 10/31/12 3:04 PM
 */
public abstract class AccountDAOTest extends AbstractDAOTest<IAccountDAO>{

    @Test
    @Override
    public void persist() {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ONE);
        account.setName("Account name");
        account.setType(AccountTypeEnum.OUTCOME);
        account.setId(1);

        getDAO().persist(account);

        Account found = getDAO().findById(1);

        assertNotNull(found);

        assertEquals(found.getName(),account.getName());
    }



    @Override
    public void findById() {
        Account account = getDAO().findById(1);

        assertNotNull(account);
        assertEquals(account.getId(),1l);
    }

    @Override
    public void delete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void getAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void searchByCriteria() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
