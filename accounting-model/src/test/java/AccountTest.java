import org.junit.Test;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author pbala
 */
public class AccountTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullName() {
        new Account(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionEmptyName() {
        new Account("", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullType() {
        new Account("account 1", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullInitialBalance() {
        new Account("account 1", AccountTypeEnum.OUTCOME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNegativeInitialBalance() {
        new Account("account 1", AccountTypeEnum.OUTCOME, new BigDecimal(-56));
    }

    @Test
    public void testConstruction() {
        Account account = new Account("account 1", AccountTypeEnum.OUTCOME, new BigDecimal(560));
        assertNotNull(account);
        assertEquals("account 1", account.getName());
        assertEquals(AccountTypeEnum.OUTCOME, account.getType());
        assertEquals(new BigDecimal(560), account.getInitialBalance());
        assertEquals(new BigDecimal(560), account.getCurrentBalance());
    }
}
