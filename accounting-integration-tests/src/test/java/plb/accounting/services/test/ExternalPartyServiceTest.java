package plb.accounting.services.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 3/17/13 11:35 PM
 */
@RunWith(JeeunitRunner.class)
public class ExternalPartyServiceTest extends AbstractServiceTest {
    @Test
    public void persist() {
        BaseExternalPartyDTO party = new ExternalPartyDTO();
        party.setName("external party 99");
        party.setDescription("external party description");
        party.setVat("1111111119");

        party = service.saveExternalParty(party);
        assertNotNull(party.getId());
    }

    @Test
    public void findById() {
        BaseExternalPartyDTO externalParty = service.searchExternalParties(new ExternalPartySearchCriteria()).get(0);
        assertNotNull(externalParty);
        assertEquals(externalParty.getId(), service.findExternalPartyById(externalParty.getId()).getId());
    }

//    @Test
    public void delete() {
        BaseExternalPartyDTO party = service.searchExternalParties(new ExternalPartySearchCriteria()).get(0);
        assertNotNull(party);
        service.deleteExternalParty(party.getId());
        assertNull(service.findExternalPartyById(party.getId()));
    }

    @Test
    public void update() {
        BaseExternalPartyDTO party = service.searchExternalParties(new ExternalPartySearchCriteria()).get(0);
        assertNotNull(party);
        party.setName("hhhhhhhhhhh");
        service.saveExternalParty(party);
        party = service.findExternalPartyById(party.getId());
        assertEquals("hhhhhhhhhhh", party.getName());
    }

    @Test
    public void getAll() {
        assertNotNull(service.searchExternalParties(new ExternalPartySearchCriteria()).get(0));
    }

    @Test
    public void searchByCriteria() {
        ExternalPartySearchCriteria criteria = new ExternalPartySearchCriteria();
        criteria.setName("org_name_2");
        List<BaseExternalPartyDTO> parties = service.searchExternalParties(criteria);
        assertEquals(1, parties.size());
        assertEquals("org_name_2", parties.get(0).getName());

        criteria.setVat("000000002");
        parties = service.searchExternalParties(criteria);
        assertEquals(1, parties.size());
        assertEquals("org_name_2", parties.get(0).getName());
        assertEquals("000000002", parties.get(0).getVat());

        criteria.setVat("000000001");
        parties = service.searchExternalParties(criteria);
        assertEquals(0, parties.size());
    }
}
