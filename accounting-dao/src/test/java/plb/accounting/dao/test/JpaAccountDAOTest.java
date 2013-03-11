package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.impl.jpa.JpaAccountDAOImpl;

/**
 * User: pbala
 * Date: 3/11/13 4:48 PM
 */
public class JpaAccountDAOTest extends AbstractAccountDAOTest{

    protected static JpaAccountDAOImpl dao;
    private  static JpaAccountDAOWrapper daoWrapper;

    @BeforeClass
    public static void setUp(){
        daoWrapper.delegate = dao;
    }

    @Override
    protected AccountDAO getDAO() {
        return daoWrapper;
    }

}
