package plb.accounting.dao;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.model.ExternalParty;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:43 PM
 */
public interface IExternalPartyDAO extends IDAO<ExternalParty>{

    List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria searchCriteria);

}
