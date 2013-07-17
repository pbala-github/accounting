package plb.accounting.dao.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.runner.RunWith;
import plb.accounting.dao.AccountDAO;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * User: pbala
 * Date: 3/11/13 4:48 PM
 */
@RunWith(JeeunitRunner.class)
public class JpaAccountDAOTest extends AbstractAccountDAOTest {

    @EJB
    private AccountDAO dao;

    @Override
    protected AccountDAO getDAO() {
        return dao;
    }

}
