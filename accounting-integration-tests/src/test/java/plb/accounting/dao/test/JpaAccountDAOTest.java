package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.impl.jpa.JpaAccountDAO;

/**
 * User: pbala
 * Date: 3/11/13 4:48 PM
 */
public class JpaAccountDAOTest extends AbstractAccountDAOTest{

    private static AccountDAO dao;

    @BeforeClass
    public static void setUp(){
        dao = JpaDaoUtil.advanceDao(new JpaAccountDAO(),AccountDAO.class);
        DataBootstrap.bootstrap(dao);
    }

    @Override
    protected AccountDAO getDAO() {
        return dao;
    }

}
