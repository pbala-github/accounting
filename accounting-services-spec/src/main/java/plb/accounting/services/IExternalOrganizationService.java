package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.BaseExternalOrganizationDTO;
import plb.accounting.dto.ExternalOrganizationDTO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:57 PM
 */
public interface IExternalOrganizationService {

    List<BaseExternalOrganizationDTO> getExternalOrganizations();

    ExternalOrganizationDTO findExternalOrganizationById(long organizationId);

    BaseExternalOrganizationDTO saveExternalOrganization(BaseExternalOrganizationDTO organization);

    void deleteExternalOrganization(long organizationId);

    List<BaseExternalOrganizationDTO> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria);

}
