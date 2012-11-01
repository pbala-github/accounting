package plb.accounting.dao.test;

import plb.accounting.dao.IAccountDAO;
import plb.accounting.dao.impl.db4o.DB4OAccountDAO;

/**
 * User: pbala
 * Date: 10/31/12 4:54 PM
 */
public class DB4OAccountDAOTest extends AccountDAOTest{

    @Override
    protected IAccountDAO getDAO() {
        return new DB4OAccountDAO();
    }
}
