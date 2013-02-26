package plb.accounting.services.impl;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.model.ExternalParty;
import plb.accounting.services.IExternalPartyService;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:24 PM
 */
public class ExternalPartyService extends  BaseService implements IExternalPartyService {


    @Override
    public List<BaseExternalPartyDTO> getExternalParties() {
        return transformationService.transform(accountingDAOFacade.getExternalParties(),BaseExternalPartyDTO.class);
    }

    @Override
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
        return transformationService.transform(accountingDAOFacade.findExternalPartyById(organizationId),ExternalPartyDTO.class);
    }

    @Override
    public ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization) {
        ExternalParty externalParty = accountingDAOFacade.saveOrUpdateExternalParty(transformationService.transform(organization,ExternalParty.class));

        return transformationService.transform(externalParty,ExternalPartyDTO.class);
    }

    @Override
    public void deleteExternalParty(long organizationId) {
        accountingDAOFacade.deleteExternalParty(organizationId);
    }

    @Override
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return transformationService.transform(accountingDAOFacade.searchExternalParties(criteria),BaseExternalPartyDTO.class);
    }
}
