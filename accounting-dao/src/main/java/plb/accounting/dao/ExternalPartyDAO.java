package plb.accounting.dao;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.view.ExternalPartyView;

import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:14 PM
 */
public interface ExternalPartyDAO extends EntityDAO {
    List<ExternalPartyView> searchExternalParties(ExternalPartySearchCriteria criteria);
}
