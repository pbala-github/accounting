package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dao.impl.jpa.JpaTransactionDAO;

/**
 * User: pbala
 * Date: 3/13/13 11:06 AM
 */
public class JpaTransactionDAOTest extends AbstractTransactionDAOTest{

    private static TransactionDAO dao;

    @BeforeClass
    public static void setUp(){
        dao = JpaDaoUtil.advanceDao(new JpaTransactionDAO(), TransactionDAO.class);
        DataBootstrap.bootstrap(dao);
    }

    @Override
    protected TransactionDAO getDAO() {
        return dao;
    }

}
