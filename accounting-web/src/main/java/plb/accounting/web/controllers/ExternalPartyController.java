package plb.accounting.web.controllers;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.services.AccountingService;
import plb.accounting.web.WebResource;

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
    List<BaseExternalPartyDTO> getExternalParties() {
        return service.getExternalParties();
    }

    /**
     *
     * @param organizationId
     * @return
     */
    ExternalPartyDTO findExternalPartyById(long organizationId) {
        return service.findExternalPartyById(organizationId);
    }

    /**
     *
     * @param organization
     * @return
     */
    ExternalPartyDTO saveExternalParty(BaseExternalPartyDTO organization) {
        return service.saveExternalParty(organization);
    }

    /**
     *
     * @param organizationId
     */
    void deleteExternalParty(long organizationId) {
        service.deleteExternalParty(organizationId);
    }

    /**
     *
     * @param criteria
     * @return
     */
    List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return service.searchExternalParties(criteria);
    }
}
