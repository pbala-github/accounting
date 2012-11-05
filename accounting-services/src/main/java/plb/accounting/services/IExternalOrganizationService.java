package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:57 PM
 */
public interface IExternalOrganizationService {

    List<ExternalOrganization> getExternalOrganizations();

    ExternalOrganization findExternalOrganizationById(long organizationId);

    ExternalOrganization saveExternalOrganization(ExternalOrganization organization);

    void deleteExternalOrganization(long organizationId);

    List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria);

}
