package plb.accounting.dao.test;

import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.impl.db4o.DB4OExternalPartyDAO;

/**
 * User: pbala
 * Date: 11/2/12 1:43 PM
 */
public class DB4OExternalPartyDAOTest extends AbstractExternalPartyDAOTest {

    @Override
    protected ExternalPartyDAO getDAO() {
        return new DB4OExternalPartyDAO();
    }
}
