package plb.accounting.dao.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.runner.RunWith;
import plb.accounting.dao.TransactionDAO;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * User: pbala
 * Date: 3/13/13 11:06 AM
 */
@RunWith(JeeunitRunner.class)
public class JpaTransactionDAOTest extends AbstractTransactionDAOTest {

    @EJB
    private TransactionDAO dao;

    @Override
    protected TransactionDAO getDAO() {
        return dao;
    }

}
