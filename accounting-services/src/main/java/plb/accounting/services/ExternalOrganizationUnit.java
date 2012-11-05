package plb.accounting.services;

import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.model.ExternalOrganization;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:24 PM
 */
public class ExternalOrganizationUnit implements IExternalOrganizationService{

    /**
     *
     */
    private IAccountingDAOFacade accountingDAOFacade;

    @Override
    public List<ExternalOrganization> getExternalOrganizations() {
        return accountingDAOFacade.getExternalOrganizations();
    }

    @Override
    public ExternalOrganization findExternalOrganizationById(long organizationId) {
        return  accountingDAOFacade.findExternalOrganizationById(organizationId);
    }

    @Override
    public ExternalOrganization saveExternalOrganization(ExternalOrganization organization) {

        if(organization.getId() == 0)
            return accountingDAOFacade.persistExternalOrganization(organization);
        else
            return accountingDAOFacade.updateExternalOrganization(organization);
    }

    @Override
    public void deleteExternalOrganization(long organizationId) {
        accountingDAOFacade.deleteExternalOrganization(organizationId);
    }

    @Override
    public List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return accountingDAOFacade.searchExternalOrganizations(criteria);
    }
}
