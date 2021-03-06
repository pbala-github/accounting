package plb.accounting.dao.test;

import static org.junit.Assert.*;
import org.junit.Test;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.IExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/2/12 1:47 PM
 */
public abstract class ExternalPartyDAOTest extends AbstractDAOTest<IExternalPartyDAO>{
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
        ExternalParty party = getDAO().findById(65l);

        assertNotNull(party);

        Transaction transaction = party.getTransactions().get(0);

        assertNotNull(transaction.getRelatedParty());

        getDAO().delete(party.getId());

        assertNull(transaction.getRelatedParty());
    }

//    @Test
    @Override
    public void update() {
        ExternalParty party = getDAO().findById(65l);

        party.setName("hhhhhhhhhhh");
        getDAO().saveOrUpdate(party);

        party = getDAO().findById(65l);

        assertEquals("hhhhhhhhhhh", party.getName());
    }

//    @Test
    @Override
    public void getAll() {
        List<ExternalParty> parties = getDAO().getAll();

        assertEquals(5, parties.size());
    }

//    @Test
    @Override
    public void searchByCriteria() {
        ExternalPartySearchCriteria criteria = new ExternalPartySearchCriteria();

        criteria.setName("org_name_2");

        List<ExternalParty> parties = getDAO().searchExternalParties(criteria);


        assertEquals(1, parties.size());
        assertEquals("org_name_2", parties.get(0).getName());

    }

}
