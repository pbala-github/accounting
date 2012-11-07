package plb.accounting.services.impl;

import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dto.BaseExternalOrganizationDTO;
import plb.accounting.dto.ExternalOrganizationDTO;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.services.IExternalOrganizationService;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:24 PM
 */
public class ExternalOrganizationService extends  BaseService implements IExternalOrganizationService {


    @Override
    public List<BaseExternalOrganizationDTO> getExternalOrganizations() {
        return transformationService.transform(accountingDAOFacade.getExternalOrganizations(),BaseExternalOrganizationDTO.class);
    }

    @Override
    public ExternalOrganizationDTO findExternalOrganizationById(long organizationId) {
        return transformationService.transform(accountingDAOFacade.findExternalOrganizationById(organizationId),ExternalOrganizationDTO.class);
    }

    @Override
    public ExternalOrganizationDTO saveExternalOrganization(ExternalOrganizationDTO organization) {
        ExternalOrganization externalOrganization = accountingDAOFacade.saveOrUpdateExternalOrganization(transformationService.transform(organization,ExternalOrganization.class));

        return transformationService.transform(externalOrganization,ExternalOrganizationDTO.class);
    }

    @Override
    public void deleteExternalOrganization(long organizationId) {
        accountingDAOFacade.deleteExternalOrganization(organizationId);
    }

    @Override
    public List<BaseExternalOrganizationDTO> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return transformationService.transform(accountingDAOFacade.searchExternalOrganizations(criteria),BaseExternalOrganizationDTO.class);
    }
}
