package plb.accounting.web.controllers;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.services.AccountingService;
import plb.accounting.web.qualifiers.WebResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User: pbala
 * Date: 3/21/13 10:07 PM
 */
@Named
@ApplicationScoped
public class ExternalPartyController {

    @Inject @WebResource
    private AccountingService service;

    /**
     *
     * @return
     */
    public List<BaseExternalPartyDTO> getExternalParties() {
        return service.getExternalParties();
    }

    /**
     *
     * @param organizationId
     * @return
     */
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
        return service.findExternalPartyById(organizationId);
    }

    /**
     *
     * @param organization
     * @return
     */
    public BaseExternalPartyDTO saveExternalParty(BaseExternalPartyDTO organization) {
        return service.saveExternalParty(organization);
    }

    /**
     *
     * @param organizationId
     */
    public void deleteExternalParty(long organizationId) {
        service.deleteExternalParty(organizationId);
    }

    /**
     *
     * @param criteria
     * @return
     */
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return service.searchExternalParties(criteria);
    }
}
