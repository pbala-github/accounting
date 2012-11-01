package plb.accounting.dao;

import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.model.ExternalOrganization;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:43 PM
 */
public interface IExternalOrganizationDAO extends IDAO<ExternalOrganization>{

    List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria searchCriteria);

}
