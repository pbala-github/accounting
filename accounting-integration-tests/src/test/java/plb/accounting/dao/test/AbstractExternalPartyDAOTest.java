package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 11/2/12 1:47 PM
 */
public abstract class AbstractExternalPartyDAOTest extends AbstractDAOTest<ExternalPartyDAO>{

//    @Test
    @Override
    public void persist() {
        ExternalParty party = new ExternalParty();
        party.setName("external party 1");
        party.setDescription("external party description");
        party.setVat("1111111111");

        party = getDAO().saveOrUpdate(party);
        assertNotNull(party.getId());
    }

//    @Test
    @Override
    public void findById() {
        ExternalParty externalParty = getDAO().getAll(ExternalParty.class).get(0);
        assertNotNull(externalParty);
        assertEquals(externalParty.getId(),getDAO().findById(ExternalParty.class,externalParty.getId()).getId());
    }

//    @Test
    @Override
    public void delete() {
        ExternalParty party = getDAO().getAll(ExternalParty.class).get(0);
        assertNotNull(party);
        getDAO().delete(ExternalParty.class,party.getId());
        assertNull(getDAO().findById(ExternalParty.class,party.getId()));
    }

    @Test
    @Override
    public void update() {
        ExternalParty party = getDAO().getAll(ExternalParty.class).get(0);
        assertNotNull(party);
        party.setName("hhhhhhhhhhh");
        getDAO().saveOrUpdate(party);
        party = getDAO().findById(ExternalParty.class,party.getId());
        assertEquals("hhhhhhhhhhh", party.getName());
    }

//    @Test
    @Override
    public void getAll() {
       assertNotNull(getDAO().getAll(ExternalParty.class));
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
