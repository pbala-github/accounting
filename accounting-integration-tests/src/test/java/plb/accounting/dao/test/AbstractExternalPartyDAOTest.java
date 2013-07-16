package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.view.ExternalPartyView;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 11/2/12 1:47 PM
 */
public abstract class AbstractExternalPartyDAOTest extends AbstractDAOTest<ExternalPartyDAO> {

    @Test
    @Override
    public void persist() {
        ExternalParty party = new ExternalParty("external party 1");
        party.setDescription("external party description");
        party.setVat("1111111111");

        party = getDAO().saveOrUpdate(party);
        assertNotNull(party.getId());
    }

    @Test
    @Override
    public void findById() {
        ExternalPartyView externalParty = getDAO().getAll().get(0);
        assertNotNull(externalParty);
        assertEquals(externalParty.getDbId(), getDAO().findById(ExternalParty.class, externalParty.getDbId()).getId());
    }

    @Test
    @Override
    public void delete() {
        ExternalPartyView party = getDAO().getAll().get(0);
        assertNotNull(party);
        getDAO().delete(ExternalParty.class, party.getDbId());
        assertNull(getDAO().findById(ExternalParty.class, party.getDbId()));
    }

    @Test
    @Override
    public void update() {
        ExternalPartyView partyView = getDAO().getAll().get(0);
        assertNotNull(partyView);
        ExternalParty party = getDAO().findById(ExternalParty.class, partyView.getDbId());

        party.setName("hhhhhhhhhhh");
        getDAO().saveOrUpdate(party);
        party = getDAO().findById(ExternalParty.class, party.getId());
        assertEquals("hhhhhhhhhhh", party.getName());
    }

    @Test
    @Override
    public void getAll() {
        List<ExternalPartyView> externalPartyViews = getDAO().getAll();
        assertNotNull(externalPartyViews);
        assertEquals(externalPartyViews.size(), DataBootstrap.MAX_EXTERNAL_PARTIES);
    }

    @Test
    @Override
    public void searchByCriteria() {
        ExternalPartySearchCriteria criteria = new ExternalPartySearchCriteria();
        criteria.setName("org_name_2");
        List<ExternalPartyView> parties = getDAO().searchExternalParties(criteria);
        assertEquals(1, parties.size());
        assertEquals("org_name_2", parties.get(0).getName());

        criteria.setVat("000000002");
        parties = getDAO().searchExternalParties(criteria);
        assertEquals(1, parties.size());
        assertEquals("org_name_2", parties.get(0).getName());
        assertEquals("000000002", parties.get(0).getVat());

        criteria.setVat("000000001");
        parties = getDAO().searchExternalParties(criteria);
        assertEquals(0, parties.size());
    }

}
