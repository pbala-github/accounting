package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.ITransactionDAO;
import plb.accounting.dao.impl.db4o.DB4OTransactionDAO;

/**
 * User: pbala
 * Date: 11/2/12 2:31 PM
 */
public class DB4OTransactionDAOTest extends TransactionDAOTest{

    private static ITransactionDAO dao;

    @BeforeClass
    public static void setup(){
        AbstractDAOTest.setup();
        dao = new DB4OTransactionDAO();
    }

    @Override
    protected ITransactionDAO getDAO() {
        return dao;
    }
}
