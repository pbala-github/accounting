package plb.accounting.dao.test;

import plb.accounting.dao.IAccountDAO;
import plb.accounting.dao.IExternalOrganizationDAO;
import plb.accounting.dao.impl.db4o.DB4OExternalOrganizationDAO;
import plb.accounting.model.ExternalOrganization;

/**
 * User: pbala
 * Date: 11/2/12 1:43 PM
 */
public class DB4OExternalOrganizationDAOTest extends ExternalOrganizationDAOTest{

    @Override
    protected IExternalOrganizationDAO getDAO() {
        return new DB4OExternalOrganizationDAO();
    }
}
