package plb.accounting.services.impl;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.model.ExternalParty;
import plb.accounting.services.IExternalPartyService;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:24 PM
 */
public class ExternalPartyService extends BaseService implements IExternalPartyService {

    private ExternalPartyDAO dao;

    @Override
    public List<BaseExternalPartyDTO> getExternalParties() {
        return transformationService.transform(dao.getAll(ExternalParty.class), BaseExternalPartyDTO.class);
    }

    @Override
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
        return transformationService.transform(dao.findById(ExternalParty.class, organizationId), ExternalPartyDTO.class);
    }

    @Override
    public ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization) {
        ExternalParty externalParty = dao.saveOrUpdate(transformationService.transform(organization, ExternalParty.class));

        return transformationService.transform(externalParty, ExternalPartyDTO.class);
    }

    @Override
    public void deleteExternalParty(long organizationId) {
        dao.delete(ExternalParty.class, organizationId);
    }

    @Override
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return transformationService.transform(dao.searchExternalParties(criteria), BaseExternalPartyDTO.class);
    }
}
