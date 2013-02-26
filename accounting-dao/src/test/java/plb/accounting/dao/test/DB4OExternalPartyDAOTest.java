package plb.accounting.dao.test;

import plb.accounting.dao.IExternalPartyDAO;
import plb.accounting.dao.impl.db4o.DB4OExternalPartyDAO;

/**
 * User: pbala
 * Date: 11/2/12 1:43 PM
 */
public class DB4OExternalPartyDAOTest extends ExternalPartyDAOTest{

    @Override
    protected IExternalPartyDAO getDAO() {
        return new DB4OExternalPartyDAO();
    }
}
