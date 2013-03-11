package plb.accounting.dao;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.model.ExternalParty;

import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:14 PM
 */
public interface ExternalPartyDAO extends EntityDAO {
    List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria);
}
