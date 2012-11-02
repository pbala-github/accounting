package plb.accounting.dao.test;

import static org.junit.Assert.*;
import org.junit.Test;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dao.IDAO;
import plb.accounting.dao.IExternalOrganizationDAO;
import plb.accounting.dao.ITransactionDAO;
import plb.accounting.dao.impl.db4o.DB4OTransactionDAO;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/2/12 1:47 PM
 */
public abstract class ExternalOrganizationDAOTest extends AbstractDAOTest<IExternalOrganizationDAO>{
    @Override
    public void persist() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Test
    @Override
    public void delete() {
        ExternalOrganization organization = getDAO().findById(65l);

        assertNotNull(organization);

        Transaction transaction = organization.getTransactions().get(0);

        assertNotNull(transaction.getRelatedOrganization());

        getDAO().delete(organization.getId());

        assertNull(transaction.getRelatedOrganization());
    }

//    @Test
    @Override
    public void update() {
        ExternalOrganization organization = getDAO().findById(65l);

        organization.setName("hhhhhhhhhhh");
        getDAO().update(organization);

        organization = getDAO().findById(65l);

        assertEquals("hhhhhhhhhhh",organization.getName());
    }

//    @Test
    @Override
    public void getAll() {
        List<ExternalOrganization> organizations = getDAO().getAll();

        assertEquals(5,organizations.size());
    }

//    @Test
    @Override
    public void searchByCriteria() {
        ExternalOrganizationSearchCriteria criteria = new ExternalOrganizationSearchCriteria();

        criteria.setName("org_name_2");

        List<ExternalOrganization> organizations = getDAO().searchExternalOrganizations(criteria);


        assertEquals(1,organizations.size());
        assertEquals("org_name_2",organizations.get(0).getName());

    }

}
