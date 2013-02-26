package plb.accounting.services;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:57 PM
 */
public interface IExternalPartyService {

    List<BaseExternalPartyDTO> getExternalParties();

    ExternalPartyDTO findExternalPartyById(long organizationId);

    ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization);

    void deleteExternalParty(long organizationId);

    List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria);

}
