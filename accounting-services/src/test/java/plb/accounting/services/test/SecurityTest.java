package plb.accounting.services.test;

import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.services.AccountingService;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static junit.framework.Assert.assertNotNull;

/**
 * User: pbala
 * Date: 4/23/13 1:52 PM
 */
public class SecurityTest {

    static AccountingService service;

    @BeforeClass
    public static void setup() throws NamingException {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.SECURITY_PRINCIPAL, "acc_user");
        properties.put(Context.SECURITY_CREDENTIALS, "acc_user");
        properties.put("org.omg.CORBA.ORBInitialPort", "9037");
        InitialContext context = new InitialContext(properties);
        service = (AccountingService) context.lookup("java:global/accounting-web/AccountingServiceImpl!plb.accounting.services.AccountingServiceRemote");
        assertNotNull(service);
    }

    /**
     *
     */
    @Test
    public void testUserAuthorizedAction() {
        assertNotNull(service.getAccounts());
    }

    @Test(expected = EJBAccessException.class)
    public void testUserUnauthorizedAction() {
        service.saveAccount(new BaseAccountDTO());
    }
}
