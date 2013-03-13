package plb.accounting.dao.test;

import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.impl.db4o.DB4OAccountDAO;

/**
 * User: pbala
 * Date: 10/31/12 4:54 PM
 */
public class DB4OAccountDAOTest extends AbstractAccountDAOTest {

    @Override
    protected AccountDAO getDAO() {
        return new DB4OAccountDAO();
    }
}
